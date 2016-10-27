package com.vklindukhov.entity;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    DRAM("Dram"), ACTION("Action"), ADVENTURE("Adventure"), CRIME("Crime");
    public String name;
    private static Map<String, Category> nameToCategory = new HashMap<>();
    private static Map<Long, Category> idToCategory = new HashMap<>();

    static {
        for (Category category : values()) {
            nameToCategory.put(category.name, category);
        }
        for (Category category : values()) {
            idToCategory.put((long) category.ordinal(), category);
        }
    }

    Category(String name) {
        this.name = name;
    }

    public static Category getByName(String name) {
        return nameToCategory.get(name);
    }

    public static Category getOne(long id) {
        return idToCategory.get(id);
    }
}
