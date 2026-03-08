package com.br.graphql.service;

import com.br.graphql.dtos.inputs.AuthorInput;
import com.br.graphql.models.Author;
import com.br.graphql.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(AuthorInput input) {
        var author = new Author(input.firstName(), input.lastName());

        return authorRepository.save(author);
    }

    public Author findAuthor(long id) {
        return authorRepository.findById(id)
                .orElse(null);
    }
}
