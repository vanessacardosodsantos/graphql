package com.br.graphql;

import java.util.Arrays;
import java.util.List;

public record Book(long id, String name, int pageCount, long authorId) {

    private static final List<Book> BOOKS = Arrays.asList(
            new Book(1L, "Harry Potter and the Philosopher's Stone", 223, 1),
            new Book(2L, "Moby Dick", 635, 2L),
            new Book(3L, "Interview with the vampire", 371, 3)
    );

    public static Book getById(long id) {
        return BOOKS.stream()
                .filter(book -> book.id() == id)
                .findFirst()
                .orElse(null);
    }
}
