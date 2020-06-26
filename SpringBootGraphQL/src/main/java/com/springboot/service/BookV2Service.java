package com.springboot.service;

import org.springframework.stereotype.Service;

import com.springboot.bean.Book;

@Service
public class BookV2Service implements IBookService {

    @Override
    public String version() {
        return "v2";
    }

    @Override
    public Book getBookDetails() {
        Book book = new Book();
        book.setId(2);
        book.setName("Book2");
        book.setVersion("version 2");
        return book;
    }

}
