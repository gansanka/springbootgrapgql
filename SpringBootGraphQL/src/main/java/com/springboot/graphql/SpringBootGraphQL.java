package com.springboot.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springboot")
public class SpringBootGraphQL {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraphQL.class, args);
    }

}
