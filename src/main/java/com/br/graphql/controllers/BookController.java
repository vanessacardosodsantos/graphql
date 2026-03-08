package com.br.graphql.controllers;

import com.br.graphql.dtos.Book;
import com.br.graphql.dtos.inputs.BooksFilter;
import com.br.graphql.models.Author;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @QueryMapping
    public Book bookById(@Argument long id) {
        return Book.getById(id);
    }

    @QueryMapping
    public List<Book> getBooks(@Argument BooksFilter filter) {
        return Book.getBooksWithFilter(filter);
    }

    @SchemaMapping
    public List<Book> publishedBooks(Author author) {
        return Book.getByAuthor(author.getId());
    }
}
