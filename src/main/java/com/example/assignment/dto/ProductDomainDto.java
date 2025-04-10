package com.example.assignment.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductDomainDto {

    private String productId;
    private String name;
    private String description;
    private Double price;
    private String categoryId;

    public ProductDomainDto() {
    }

    public ProductDomainDto(String productId, String name, String description, Double price, String categoryId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public static interface ProductIdStep {
        NameStep withProductId(String productId);
    }

    public static interface NameStep {
        DescriptionStep withName(String name);
    }

    public static interface DescriptionStep {
        PriceStep withDescription(String description);
    }

    public static interface PriceStep {
        CategoryIdStep withPrice(Double price);
    }

    public static interface CategoryIdStep {
        BuildStep withCategoryId(String categoryId);
    }

    public static interface BuildStep {
        ProductDomainDto build();
    }

    public static class Builder
        implements ProductIdStep, NameStep, DescriptionStep, PriceStep, CategoryIdStep, BuildStep {
        private String productId;
        private String name;
        private String description;
        private Double price;
        private String categoryId;

        private Builder() {
        }

        public static ProductIdStep productDomainDto() {
            return new Builder();
        }

        @Override
        public NameStep withProductId(String productId) {
            this.productId = productId;
            return this;
        }

        @Override
        public DescriptionStep withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public PriceStep withDescription(String description) {
            this.description = description;
            return this;
        }

        @Override
        public CategoryIdStep withPrice(Double price) {
            this.price = price;
            return this;
        }

        @Override
        public BuildStep withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        @Override
        public ProductDomainDto build() {
            return new ProductDomainDto(
                this.productId,
                this.name,
                this.description,
                this.price,
                this.categoryId
            );
        }
    }
}
