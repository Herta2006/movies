package com.vklindukhov.dao;

import com.vklindukhov.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String name);

    List<Movie> findByDescription(String description);

    List<Movie> findByCategoryName(String categoryName);

    List<Movie> findByLanguageName(String languageName);

    List<Movie> findByActorsName(String actorName);

    @Query("" +
            "SELECT m " +
            "FROM Movie m " +
            "JOIN m.actors movieActors " +
            "WHERE m.title = :str " +
            "OR m.description = :str " +
            "OR m.description = :str " +
            "OR m.categoryName = :str " +
            "OR m.languageName = :str " +
            "OR movieActors.name = :str " +
            "GROUP BY m " +
            ""
    )
    List<Movie> find(@Param("str") String str);
}


