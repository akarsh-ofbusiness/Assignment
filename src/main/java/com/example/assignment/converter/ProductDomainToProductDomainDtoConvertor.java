package com.example.assignment.converter;

import com.example.assignment.domain.Product;
import com.example.assignment.dto.ProductDomainDto;
import org.springframework.util.ObjectUtils;

public class ProductDomainToProductDomainDtoConvertor {

    public static ProductDomainDto convert(Product product) {
        if (ObjectUtils.isEmpty(product)) {
            return null;
        }
        return ProductDomainDto.Builder.productDomainDto()
            .withProductId(product.getProductId())
            .withName(product.getName())
            .withDescription(product.getDescription())
            .withPrice(product.getPrice())
            .withCategoryId(product.getCategoryId())
            .build();
    }
}
