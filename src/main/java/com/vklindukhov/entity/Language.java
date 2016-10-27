package com.vklindukhov.entity;

import java.util.HashMap;
import java.util.Map;

public enum Language {
    RUSSIAN("Russian"), ENGLISH("English");
    public String name;
    private static Map<String, Language> nameToLanguage = new HashMap<>();
    private static Map<Long, Language> idToLanguage = new HashMap<>();
    static {
        for (Language language : values()) {
            nameToLanguage.put(language.name, language);
        }
        for (Language language : values()) {
            idToLanguage.put((long) language.ordinal(), language);
        }
    }

    Language(String name) {
        this.name = name;
    }

    public static Language getByName(String name) {
        return nameToLanguage.get(name);
    }

    public static Language getOne(long id) {
        return idToLanguage.get(id);
    }
}
