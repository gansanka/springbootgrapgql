package com.springboot.datafetcher;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.bean.Book;
import com.springboot.service.BookV1Service;
import com.springboot.service.IBookService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    List<IBookService> serviceList;

    @Override
    public Book get(DataFetchingEnvironment environment) throws Exception {
        String ver = environment.getArgument("version");

        Supplier<IBookService> supplier = () -> {
            return new BookV1Service();
        };

        IBookService service = serviceList.stream().filter(
                item -> item.version().equalsIgnoreCase(
                        ver)).findFirst().orElseGet(supplier);

        return service.getBookDetails();
    }

}
