package com.example.assignment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryDomainDto {

    private String categoryId;
    private String name;

    public CategoryDomainDto() {
    }

    public CategoryDomainDto(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static interface CategoryIdStep {
        NameStep withCategoryId(String categoryId);
    }

    public static interface NameStep {
        BuildStep withName(String name);
    }

    public static interface BuildStep {
        CategoryDomainDto build();
    }

    public static class Builder implements CategoryIdStep, NameStep, BuildStep {
        private String categoryId;
        private String name;

        private Builder() {
        }

        public static CategoryIdStep categoryDomainDto() {
            return new Builder();
        }

        @Override
        public NameStep withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        @Override
        public BuildStep withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public CategoryDomainDto build() {
            return new CategoryDomainDto(
                this.categoryId,
                this.name
            );
        }
    }
}
