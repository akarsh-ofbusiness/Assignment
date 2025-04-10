package com.example.assignment.service;

import com.example.assignment.converter.CategoryDomainToCategoryDomainDto;
import com.example.assignment.domain.Category;
import com.example.assignment.dto.CategoryDomainDto;
import com.example.assignment.repository.CategoryRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(CategoryDomainDto dto) {
        categoryRepository.save(Category.Builder.category()
            .withCategoryId(dto.getCategoryId())
            .withCategoryName(dto.getCategoryId())
            .build());
    }

    public List<String> getAllCategories() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryDomainToCategoryDomainDto::convert).filter(Objects::nonNull)
            .map(CategoryDomainDto::getName)
            .collect(Collectors.toList());
    }
}
