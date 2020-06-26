package com.springboot.service;

import org.springframework.stereotype.Service;

import com.springboot.bean.Book;

@Service
public class BookV1Service implements IBookService {

    @Override
    public String version() {
        return "v1";
    }

    @Override
    public Book getBookDetails() {
        Book book = new Book();
        book.setId(1);
        book.setName("Book1");
        book.setVersion("version 1");
        return book;
    }

}
