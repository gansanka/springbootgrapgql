package com.springboot.graphql;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.springboot.bean.Author;
import com.springboot.bean.Book;
import com.springboot.bean.Result;
import com.springboot.datafetcher.AuthorDataFetcher;
import com.springboot.datafetcher.BookDataFetcher;
import com.springboot.datafetcher.ResultsDataFetcher;

import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.AsyncDataFetcher;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class SpringBootGraphQLProvider {

    @Autowired
    ResultsDataFetcher resultsDataFetcher;

    @Autowired
    BookDataFetcher bookDataFetcher;

    @Autowired
    AuthorDataFetcher authorDataFetcher;

    GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);

        this.graphQL = GraphQL.newGraphQL(graphQLSchema).queryExecutionStrategy(
                new AsyncExecutionStrategy()).build();
    }

    public GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildAsyncWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry,
                runtimeWiring);
    }

    public RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring().type(newTypeWiring(
                "Query").dataFetcher("overview", resultsDataFetcher)).type(
                        newTypeWiring("book").dataFetcher("Result",
                                bookDataFetcher)).type(
                                        newTypeWiring("Result").dataFetcher(
                                                "author",
                                                authorDataFetcher)).build();
    }

    public RuntimeWiring buildAsyncWiring() {
        DataFetcher<CompletableFuture<Result>> resultAsyncDataFetcher = AsyncDataFetcher.async(
                resultsDataFetcher);
        DataFetcher<CompletableFuture<Book>> bookAsyncDataFetcher = AsyncDataFetcher.async(
                bookDataFetcher);
        DataFetcher<CompletableFuture<Author>> authorAsyncDataFetcher = AsyncDataFetcher.async(
                authorDataFetcher);
        return RuntimeWiring.newRuntimeWiring().type(newTypeWiring(
                "Query").dataFetcher("overview", resultAsyncDataFetcher)).type(
                        newTypeWiring("Result").dataFetcher("book",
                                bookAsyncDataFetcher)).type(
                                        newTypeWiring("Result").dataFetcher(
                                                "author",
                                                authorAsyncDataFetcher)).build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

}
