package com.bg.graphql.service;

import com.bg.graphql.model.Book;
import com.bg.graphql.repository.BookRepository;
import com.bg.graphql.service.datafetcher.AllBooksDataFetcher;
import com.bg.graphql.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

//Data fetcher
@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;

    @Autowired
    private BookDataFetcher bookDataFetcher;

    @Autowired
    BookRepository bookRepository;

    // how to add in live template
    @PostConstruct
    private void loadSchema() throws IOException {

        // Load books in repository
        loadDataInHSQL();
        // get the schema
        File schemaFile = resource.getFile();

        //parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();

    }

    private void loadDataInHSQL() {
        Stream.of(new Book("ISN_101", "SDIP", "Stefan", new String[]{"Abhaye"}, "June 2019")
                ,new Book("ISN_102", "LDP", "Tanuja", new String[]{"Yash"}, "June 2018")
                ,new Book("ISN_103", "SWP", "Rafeal", new String[]{"Frida"}, "June 2017")
                ,new Book("ISN_104", "SWIP", "Bhavesh", new String[]{"Raj"}, "June 2016")
        ).forEach(book -> bookRepository.save(book));
    }

    private RuntimeWiring buildRuntimeWring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query",
                        typeWiring-> typeWiring
                            .dataFetcher("allBooks", allBooksDataFetcher)
                            .dataFetcher("book", bookDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
