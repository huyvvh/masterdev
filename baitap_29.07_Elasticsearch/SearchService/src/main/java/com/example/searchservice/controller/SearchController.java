package com.example.searchservice.controller;

import com.example.searchservice.repository.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/get/{keyword}/{size}", method = RequestMethod.GET)
    public List<String> getSuggestion(@PathVariable String keyword, @PathVariable int size) throws IOException {
        Service service = context.getBean(Service.class);
        return service.getSuggestion(keyword, size);
    }
}
