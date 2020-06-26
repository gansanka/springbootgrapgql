package com.springboot.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.bean.Result;
import com.springboot.service.ResultService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ResultsDataFetcher implements DataFetcher<Result> {

    @Autowired
    ResultService service;

    @Override
    public Result get(DataFetchingEnvironment environment) throws Exception {
        return service.getResults();
    }

}
