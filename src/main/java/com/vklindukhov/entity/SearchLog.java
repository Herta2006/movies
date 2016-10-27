package com.vklindukhov.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "search_logs")
public class SearchLog {
    @Id
    @GeneratedValue
    private Long id;
    private String facebookId;
    @ElementCollection
    private List<String> fieldsToSearch;
    @ElementCollection
    private List<Movie> foundMovies;
    private Date time;
    private int resultCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public List<String> getFieldsToSearch() {
        return fieldsToSearch;
    }

    public void setFieldsToSearch(List<String> fieldsToSearch) {
        this.fieldsToSearch = fieldsToSearch;
    }

    public List<Movie> getFoundMovies() {
        return foundMovies;
    }

    public void setFoundMovies(List<Movie> foundMovies) {
        this.foundMovies = foundMovies;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchLog searchLog = (SearchLog) o;

        return id != null ? id.equals(searchLog.id) : searchLog.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
