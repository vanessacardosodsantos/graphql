package com.br.graphql.service;

import com.br.graphql.models.Genre;
import com.br.graphql.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreService {

    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<Genre> findAllByIds(Set<Integer> genreIds) {
        return repository.findAllById(genreIds);
    }

}
