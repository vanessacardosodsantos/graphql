package com.br.graphql.service;

import com.br.graphql.models.BookGenre;
import com.br.graphql.models.Genre;
import com.br.graphql.repository.BookGenreRepository;
import com.br.graphql.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreService {

    private final GenreRepository repository;

    private final BookGenreRepository bookGenreRepository;

    public GenreService(GenreRepository repository, BookGenreRepository bookGenreRepository) {
        this.repository = repository;
        this.bookGenreRepository = bookGenreRepository;
    }

    public List<Genre> findAllByIds(Set<Integer> genreIds) {
        return repository.findAllById(genreIds);
    }

    public List<BookGenre> findAllByBookIdIn(List<Long> bookIds) {
        return bookGenreRepository.findAllByBookIdIn(bookIds);
    }
}
