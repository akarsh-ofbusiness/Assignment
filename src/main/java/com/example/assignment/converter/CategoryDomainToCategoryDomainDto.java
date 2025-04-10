package com.example.assignment.converter;

import com.example.assignment.domain.Category;
import com.example.assignment.dto.CategoryDomainDto;
import org.springframework.util.ObjectUtils;

public class CategoryDomainToCategoryDomainDto {

    public static CategoryDomainDto convert(Category category) {
        if (ObjectUtils.isEmpty(category)) {
            return null;
        }
        return CategoryDomainDto.Builder.categoryDomainDto()
            .withCategoryId(category.getCategoryId())
            .withName(category.getCategoryName())
            .build();
    }
}
