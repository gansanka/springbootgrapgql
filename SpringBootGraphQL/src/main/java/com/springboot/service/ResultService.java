package com.springboot.service;

import org.springframework.stereotype.Service;

import com.springboot.bean.Author;
import com.springboot.bean.Book;
import com.springboot.bean.Result;

@Service
public class ResultService {

    public Result getResults() {
        Result result = new Result();
        result.setAnswered("yes");
        result.setAuthor(new Author());
        result.setBook(new Book());
        return result;
    }

}
