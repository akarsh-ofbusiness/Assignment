package com.example.assignment.service;

import com.example.assignment.exceptions.ResourceNotFoundException;
import com.example.assignment.converter.ProductDomainToProductDomainDtoConvertor;
import com.example.assignment.domain.Category;
import com.example.assignment.domain.Product;
import com.example.assignment.dto.ProductDomainDto;
import com.example.assignment.repository.CategoryRepository;
import com.example.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDomainDto dto) throws Exception {
        Category category = categoryRepository.findByCategoryId(dto.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        productRepository.save(Product.Builder.product()
            .withProductId(dto.getProductId())
            .withName(dto.getName())
            .withDescription(dto.getDescription())
            .withPrice(dto.getPrice())
            .withCategoryId(dto.getCategoryId())
            .build());
    }

    public Page<ProductDomainDto> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageable).map(ProductDomainToProductDomainDtoConvertor::convert);
    }

    public ProductDomainDto getProducts(String productId) {
        return ProductDomainToProductDomainDtoConvertor.convert(productRepository.findByProductId(productId));
    }
}
