package com.vklindukhov.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "movies")
@XmlRootElement(name = "player")
public final class Movie implements SearchEntity {
    @Id
    @GeneratedValue
    private Long id;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private String categoryName;
    @XmlElement
    private Short releaseYear;
    @XmlElement
    private String languageName;
    @XmlElement
    private Short length;
    @XmlElement
    private Float rating;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actors")
    @XmlElement
    private List<Actor> actors;

    public Movie(String title, String description, String categoryName, String languageName, List<Actor> actors) {
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
        this.languageName = languageName;
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id != null ? id.equals(movie.id) : movie.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
