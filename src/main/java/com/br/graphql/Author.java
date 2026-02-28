package com.br.graphql;

import java.util.Arrays;
import java.util.List;

public record Author(long id, String firstName, String lastName) {

    private static final List<Author> AUTHORS = Arrays.asList(
            new Author(1L, "Joanne", "Rowling"),
            new Author(2L, "Herman", "Melville"),
            new Author(3L, "Anne", "Rice")
    );

    public static Author getById(long id) {
        return AUTHORS.stream()
                .filter(author -> author.id() == id)
                .findFirst()
                .orElse(null);
    }
}
