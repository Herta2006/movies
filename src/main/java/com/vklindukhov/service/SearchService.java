package com.vklindukhov.service;

import com.vklindukhov.entity.Movie;
import com.vklindukhov.entity.SearchEntity;

import java.util.List;

public interface SearchService {
    List<Movie> findMovies(String userFacebookIdWhoAsk, String str);

    List<? extends SearchEntity> findAll(Class<? extends SearchEntity> clazz);

    <T extends SearchEntity> T findByName(Class<? extends SearchEntity> clazz, String name);

    <T extends SearchEntity> T findById(Class<? extends SearchEntity> clazz, long id);
}
