package com.br.graphql.dtos;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public record Book(long id, String name, int pageCount, Set<Genre> genres, long authorId) {

    private static final List<Book> BOOKS = Arrays.asList(
            new Book(1L, "Harry Potter and the Philosopher's Stone", 223, Set.of(Genre.ACTION, Genre.ADVENTURE),1),
            new Book(2L ,"Harry Potter and the Chamber of Secrets", 300, Set.of(Genre.ACTION, Genre.ADVENTURE), 1),
            new Book(3L, "Moby Dick", 635, Set.of(Genre.ADVENTURE), 2L),
            new Book(4L, "Interview with the vampire", 371, Set.of(Genre.TERROR), 3)
    );

    public static Book getById(long id) {
        return BOOKS.stream()
                .filter(book -> book.id() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<Book> getByAuthor(long id) {
        return BOOKS.stream()
                .filter(book -> book.authorId == id)
                .toList();
    }

    public static List<Book> getBooksWithFilter(BooksFilter filter) {
        if(filter == null)
            return List.copyOf(BOOKS);

        return BOOKS.stream()
                .filter(filter::isBookValid)
                .toList();
    }
}
