package com.vklindukhov.service;

import com.vklindukhov.dao.MovieRepository;
import com.vklindukhov.dao.SearchLogRepository;
import com.vklindukhov.entity.Movie;
import com.vklindukhov.entity.SearchLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SearchLogRepository searchLogRepository;

    @Override
    public void find(String userFacebookIdWhoAsk, String str) {
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
}
