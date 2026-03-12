package com.br.graphql.dataloader;

import org.springframework.stereotype.Component;
import com.br.graphql.models.Author;
import com.br.graphql.repository.AuthorRepository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AuthorDataLoader {

    public static final String NAME = "authorDataLoader";

    private final AuthorRepository authorRepository;

    public AuthorDataLoader(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

   public Mono<Map<Long, Author>> load(Set<Long> idsAuthors) {
        return Mono.fromCallable(() ->
                    authorRepository.findAllById(idsAuthors)
                            .stream()
                            .collect(Collectors.toMap(Author::getId, Function.identity()))
                );
   }
}
