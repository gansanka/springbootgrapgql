package com.springboot.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.bean.Author;
import com.springboot.service.AuthorService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AuthorDataFetcher implements DataFetcher<Author> {

    @Autowired
    AuthorService service;

    @Override
    public Author get(DataFetchingEnvironment environment) throws Exception {
        return service.getAuthorDetails();
    }

}
