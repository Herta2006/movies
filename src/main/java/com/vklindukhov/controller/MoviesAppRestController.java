package com.vklindukhov.controller;

import com.vklindukhov.entity.CategoryWrapper;
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

    //-------------------Retrieve All--------------------------------------------------------

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/categories/", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryWrapper>> categories() {
        List<CategoryWrapper> categories = (List<CategoryWrapper>) searchService.findAll(CategoryWrapper.class);
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //-------------------Retrieve One--------------------------------------------------------

    @RequestMapping(value = "/categories/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CategoryWrapper> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        CategoryWrapper category = searchService.findById(CategoryWrapper.class, id);
        if (category == null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}