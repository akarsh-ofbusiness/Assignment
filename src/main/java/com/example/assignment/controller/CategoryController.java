package com.example.assignment.controller;

import com.example.assignment.utils.RestResponse;
import com.example.assignment.dto.CategoryDomainDto;
import com.example.assignment.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "createCategory")
    public ResponseEntity<RestResponse<String>> create(@RequestBody CategoryDomainDto dto) {
        try {
            categoryService.createCategory(dto);

            RestResponse<String> response = new RestResponse<>();
            response.setSuccess(true);
            response.setMessage("Category created successfully");
            response.setData("SUCCESS");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RestResponse<String> errorResponse = new RestResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Failed to create category: " + e.getMessage());
            errorResponse.setData(null);

            return ResponseEntity.ok(errorResponse);
        }
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<RestResponse<List<String>>> getAll() {
        try {
            List<String> categories = categoryService.getAllCategories();

            RestResponse<List<String>> response = new RestResponse<>();
            response.setSuccess(true);
            response.setMessage("Categories fetched successfully");
            response.setData(categories);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RestResponse<List<String>> errorResponse = new RestResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Failed to fetch categories: " + e.getMessage());
            errorResponse.setData(null);

            return ResponseEntity.ok(errorResponse);
        }
    }
}
