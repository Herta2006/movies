package com.vklindukhov.entity;

public final class CategoryWrapper implements SearchEntity {
    private final Category category;

    public CategoryWrapper(Category category) {
        this.category = category;
    }

    public String getName() {
        return category.name;
    }

    public Category getCategory() {
        return category;
    }
}