package com.vklindukhov.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

public final class LanguageWrapper implements SearchEntity {
    private final Language language;

    public LanguageWrapper(Language language) {
        this.language = language;
    }

    public String getName() {
        return language.name;
    }

    public Language getLanguage() {
        return language;
    }
}