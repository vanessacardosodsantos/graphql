package com.br.graphql.controllers;

import com.br.graphql.models.Book;
import com.br.graphql.models.BookGenre;
import com.br.graphql.models.Genre;
import com.br.graphql.service.BookService;
import com.br.graphql.service.dtos.inputs.BookInput;
import com.br.graphql.service.dtos.inputs.BooksFilter;
import com.br.graphql.models.Author;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    @BatchMapping
    public Map<Book, List<Genre>> genres(List<Book> books) {

        List<Long> idsBooks = books.stream()
                .map(Book::getId)
                .toList();

        List<BookGenre> bookGenres = bookService.findGenresByIdsBooks(idsBooks);

        Map<Long, List<Genre>> genresGroupedByBookId = bookGenres.stream()
                .collect(Collectors.groupingBy(
                        BookGenre::getBookId,
                        Collectors.mapping(BookGenre::getGenre, Collectors.toList())
                ));

        return books.stream()
                .collect(Collectors.toMap(
                        book -> book,
                        book -> genresGroupedByBookId.get(book.getId()
                        )));
    }

    @SchemaMapping
    public CompletableFuture<Author> author(Book book, DataLoader<Long, Author> authorLoader) {
        return authorLoader.load(book.getAuthorId());
    }
}
