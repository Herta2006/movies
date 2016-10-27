package com.vklindukhov.service;

import com.vklindukhov.dao.ActorRepository;
import com.vklindukhov.dao.MovieRepository;
import com.vklindukhov.dao.SearchLogRepository;
import com.vklindukhov.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@SuppressWarnings("all")
public class SearchServiceImpl implements SearchService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private SearchLogRepository searchLogRepository;

    @Override
    public void findMovies(String userFacebookIdWhoAsk, String str) {
        Date time = new Date();
        List<Movie> found = movieRepository.find(str);
        SearchLog entity = new SearchLog();
        entity.setFacebookId(userFacebookIdWhoAsk);
        entity.setResultCount(found.size());
        entity.setTime(time);
        entity.setFieldsToSearch(Arrays.asList("title", "description", "category_name", "language_name", "actor_name"));
        entity.setFoundMovies(found);
        SearchLog saved = searchLogRepository.save(entity);
    }

    @Override
    public List<? extends SearchEntity> findAll(Class<? extends SearchEntity> clazz) {
        if (clazz == CategoryWrapper.class)
            return Stream.of(Category.values()).map(CategoryWrapper::new).collect(Collectors.toList());
        else if (clazz == LanguageWrapper.class)
            return Stream.of(Language.values()).map(LanguageWrapper::new).collect(Collectors.toList());
        else if (clazz == Actor.class) return new ArrayList<>(actorRepository.findAll());
        else if (clazz == Movie.class) return new ArrayList<>(movieRepository.findAll());
        else return Collections.EMPTY_LIST;
    }

    @Override
    public <T extends SearchEntity> T findByName(Class<? extends SearchEntity> clazz, String name) {
        if (clazz == CategoryWrapper.class) return (T) new CategoryWrapper(Category.getByName(name));
        else if (clazz == LanguageWrapper.class) return (T) new LanguageWrapper(Language.getByName(name));
        else if (clazz == Actor.class) return (T) actorRepository.findByName(name).get(0);
        else if (clazz == Movie.class) return (T) movieRepository.findByTitle(name).get(0);
        else return null;
    }

    @Override
    public <T extends SearchEntity> T findById(Class<? extends SearchEntity> clazz, long id) {
        if (clazz == CategoryWrapper.class) return (T) new CategoryWrapper(Category.getOne(id));
        else if (clazz == LanguageWrapper.class) return (T) new LanguageWrapper(Language.getOne(id));
        else if (clazz == Actor.class) return (T) actorRepository.getOne(id);
        else if (clazz == Movie.class) return (T) movieRepository.getOne(id);
        else return null;
    }
}
