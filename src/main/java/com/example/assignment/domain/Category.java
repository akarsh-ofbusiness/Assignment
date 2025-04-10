package com.example.assignment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "category")
@Table(name = "category", indexes = {
    @Index(name = "categoryId", columnList = "categoryId", unique = true),
})
@Data
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String categoryId;

    private String categoryName;

    public Category() {
    }

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static interface CategoryIdStep {
        CategoryNameStep withCategoryId(String categoryId);
    }

    public static interface CategoryNameStep {
        BuildStep withCategoryName(String categoryName);
    }

    public static interface BuildStep {
        Category build();
    }

    public static class Builder implements CategoryIdStep, CategoryNameStep, BuildStep {
        private String categoryId;
        private String categoryName;

        private Builder() {
        }

        public static CategoryIdStep category() {
            return new Builder();
        }

        @Override
        public CategoryNameStep withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        @Override
        public BuildStep withCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        @Override
        public Category build() {
            return new Category(
                this.categoryId,
                this.categoryName
            );
        }
    }
}
