package com.br.graphql.service;

import com.br.graphql.service.dtos.inputs.AuthorInput;
import com.br.graphql.exceptions.GraphQLEntityNotFoundException;
import com.br.graphql.models.Author;
import com.br.graphql.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Author updateAuthor(long id, AuthorInput author) {
        Optional<Author> optional = authorRepository.findById(id);

        if(optional.isEmpty())
            throw new GraphQLEntityNotFoundException("Author not found");

        /*
            Sem validação no update apenas para facilitar, a ideia aqui é apenas mostrar
            o GraphQL funcionando
         */
        Author savedAuthor = optional.get();

        savedAuthor.setFirstName(author.firstName());
        savedAuthor.setLastName(author.lastName());

        return authorRepository.save(savedAuthor);
    }

    public Author findAuthor(long id) {
        return authorRepository.findById(id)
                .orElse(null);
    }

    public boolean deleteAuthor(long id) {
        if(!authorRepository.existsById(id)) {
            throw new GraphQLEntityNotFoundException("Author not found");
        }

        authorRepository.deleteById(id);
        return true;
    }
}
