package com.example.assignment.controller;

import com.example.assignment.utils.RestResponse;
import com.example.assignment.dto.ProductDomainDto;
import com.example.assignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "createProduct")
    public ResponseEntity<RestResponse<String>> create(@RequestBody ProductDomainDto dto) {
        try {
            productService.createProduct(dto);

            RestResponse<String> response = new RestResponse<>();
            response.setSuccess(true);
            response.setMessage("Product created successfully");
            response.setData("SUCCESS");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RestResponse<String> errorResponse = new RestResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Failed to create product: " + e.getMessage());
            errorResponse.setData(null);

            return ResponseEntity.ok(errorResponse);
        }
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<RestResponse<Page<ProductDomainDto>>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        try {
            Page<ProductDomainDto> productPage = productService.getAllProducts(page, size, sortBy);

            RestResponse<Page<ProductDomainDto>> response = new RestResponse<>();
            response.setData(productPage);
            response.setMessage("Products fetched successfully");
            response.setSuccess(true);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RestResponse<Page<ProductDomainDto>> errorResponse = new RestResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Failed to fetch products: " + e.getMessage());
            errorResponse.setData(null);

            return ResponseEntity.ok(errorResponse);
        }
    }

    @GetMapping(value = "getProduct/{productId}")
    public ResponseEntity<RestResponse<ProductDomainDto>> getProduct(@PathVariable String productId) {
        try {
            ProductDomainDto product = productService.getProducts(productId);

            RestResponse<ProductDomainDto> response = new RestResponse<>();
            response.setData(product);
            response.setMessage("Products fetched successfully");
            response.setSuccess(true);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RestResponse<ProductDomainDto> errorResponse = new RestResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Failed to fetch products: " + e.getMessage());
            errorResponse.setData(null);

            return ResponseEntity.ok(errorResponse);
        }
    }
}
