package com.vklindukhov.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "language")
public final class LanguageWrapper implements SearchEntity {
    private final Language language;

    public LanguageWrapper(Language language) {
        this.language = language;
    }

    @XmlValue
    public String getValue() {
        return language.name;
    }

    @XmlAttribute
    public Language getLanguage() {
        return language;
    }
}