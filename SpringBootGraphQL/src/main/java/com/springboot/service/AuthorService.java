package com.springboot.service;

import org.springframework.stereotype.Service;

import com.springboot.bean.Author;

@Service
public class AuthorService {

    public Author getAuthorDetails() {
        Author author = new Author();
        author.setName("John");
        return author;
    }

}
