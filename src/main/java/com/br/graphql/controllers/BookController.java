package com.br.graphql.controllers;

import com.br.graphql.models.Book;
import com.br.graphql.service.BookService;
import com.br.graphql.service.dtos.inputs.BookInput;
import com.br.graphql.service.dtos.inputs.BooksFilter;
import com.br.graphql.models.Author;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @MutationMapping
    public Book createBook(@Argument BookInput book) {
        return bookService.createBook(book);
    }

    @MutationMapping
    public Book updateBook(@Argument long id, @Argument BookInput book) {
        return bookService.updateBook(id, book);
    }

    @MutationMapping
    public boolean deleteBook(@Argument long id) {
        return bookService.deleteBook(id);
    }

    @QueryMapping
    public Book bookById(@Argument long id) {
        return bookService.findBookById(id);
    }

    @QueryMapping
    public List<Book> getBooks(@Argument BooksFilter filter) {
        return bookService.findBooks(filter);
    }

    @SchemaMapping
    public List<Book> publishedBooks(Author author) {
        return bookService.findBookByAuthor(author);
    }
}
