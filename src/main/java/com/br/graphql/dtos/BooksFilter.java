package com.br.graphql.dtos;

import java.util.Collections;
import java.util.List;

public record BooksFilter(List<Genre> genres, Integer maxPages) {
    public boolean isBookValid(Book book) {
        boolean isPageQtndValid = maxPages != null && book.pageCount() <= maxPages;
        boolean isValidGenres = genres != null && (genres.isEmpty() || atLeastOneGenreValid(book, genres));

        return isPageQtndValid && isValidGenres;
    }

    private boolean atLeastOneGenreValid(Book book, List<Genre> genres) {
        return !Collections.disjoint(book.genres(), genres);
    }
}
