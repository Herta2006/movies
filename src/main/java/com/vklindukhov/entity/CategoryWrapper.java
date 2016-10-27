package com.vklindukhov.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "category")
public final class CategoryWrapper implements SearchEntity {
    private final Category category;

    public CategoryWrapper(Category category) {
        this.category = category;
    }

    @XmlValue
    public String getName() {
        return category.name;
    }

    @XmlAttribute
    public Category getCategory() {
        return category;
    }
}