package com.vklindukhov.controller;

import com.vklindukhov.entity.Actor;
import com.vklindukhov.entity.CategoryWrapper;
import com.vklindukhov.entity.LanguageWrapper;
import com.vklindukhov.entity.Movie;
import com.vklindukhov.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SuppressWarnings("unused")
public class MoviesAppRestController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/")
    public String welcome() {
        //Welcome page, non-rest
        return "Welcome to Movies App!!!";
    }


    //-------------------Retrieve All Categories--------------------------------------------------------

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryWrapper>> categories() {
        List<CategoryWrapper> categories = (List<CategoryWrapper>) searchService.findAll(CategoryWrapper.class);
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //-------------------Retrieve One Category--------------------------------------------------------

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CategoryWrapper> category(@PathVariable("id") long id) {
        System.out.println("Fetching Category with id " + id);
        CategoryWrapper category = searchService.findById(CategoryWrapper.class, id);
        if (category == null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    //-------------------Retrieve All Languages--------------------------------------------------------

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public ResponseEntity<List<LanguageWrapper>> languages() {
        List<LanguageWrapper> languages = (List<LanguageWrapper>) searchService.findAll(LanguageWrapper.class);
        if (languages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }

    //-------------------Retrieve One Language--------------------------------------------------------

    @RequestMapping(value = "/languages/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<LanguageWrapper> language(@PathVariable("id") long id) {
        System.out.println("Fetching Language with id " + id);
        LanguageWrapper language = searchService.findById(LanguageWrapper.class, id);
        if (language == null) {
            System.out.println("Language with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    //-------------------Retrieve All Actors--------------------------------------------------------

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public ResponseEntity<List<Actor>> actors() {
        List<Actor> actors = (List<Actor>) searchService.findAll(Actor.class);
        if (actors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    //-------------------Retrieve One Actor--------------------------------------------------------

    @RequestMapping(value = "/actors/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Actor> actor(@PathVariable("id") long id) {
        System.out.println("Fetching Language with id " + id);
        Actor actor = searchService.findById(Actor.class, id);
        if (actor == null) {
            System.out.println("Actor with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    //-------------------Retrieve All Movies--------------------------------------------------------

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> movies() {
        List<Movie> movies = (List<Movie>) searchService.findAll(Movie.class);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    //-------------------Retrieve One Actor--------------------------------------------------------

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Movie> movie(@PathVariable("id") long id) {
        System.out.println("Fetching Movie with id " + id);
        Movie movie = searchService.findById(Movie.class, id);
        if (movie == null) {
            System.out.println("Actor with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

}