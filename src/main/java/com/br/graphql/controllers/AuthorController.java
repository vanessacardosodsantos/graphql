package com.br.graphql.controllers;

import com.br.graphql.models.Book;
import com.br.graphql.service.dtos.inputs.AuthorInput;
import com.br.graphql.models.Author;
import com.br.graphql.service.AuthorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @MutationMapping
    public Author createAuthor(@Argument AuthorInput author) {
        return authorService.createAuthor(author);
    }

    @MutationMapping
    public Author updateAuthor(@Argument long id, @Argument AuthorInput author) {
        return authorService.updateAuthor(id, author);
    }

    @MutationMapping
    public boolean deleteAuthor(@Argument long id) {
        return authorService.deleteAuthor(id);
    }

    @QueryMapping
    public Author authorById(@Argument long id) {
        return authorService.findAuthor(id);
    }

    @SchemaMapping
    public String name(Author author) {
        return author.getFirstName() +  " " + author.getLastName();
    }
}
