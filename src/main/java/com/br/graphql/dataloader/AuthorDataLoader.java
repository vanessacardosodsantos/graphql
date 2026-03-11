package com.br.graphql.dataloader;

import org.springframework.stereotype.Component;
import com.br.graphql.models.Author;
import com.br.graphql.repository.AuthorRepository;
import org.dataloader.BatchLoader;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class AuthorDataLoader implements BatchLoader<Long, Author> {

    public static final String NAME = "authorDataLoader";

    private final AuthorRepository authorRepository;

    public AuthorDataLoader(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public CompletionStage<List<Author>> load(List<Long> authorIds) {
        return CompletableFuture.supplyAsync(() -> {
            // Busca todos os autores de uma vez: SELECT * FROM author WHERE id IN (...)
            Iterable<Author> authors = authorRepository.findAllById(authorIds);

            Map<Long, Author> authorMap = StreamSupport
                    .stream(authors.spliterator(), false)
                    .collect(Collectors.toMap(Author::getId, author -> author));

            // Retorna na mesma ordem dos IDs recebidos (exigência do DataLoader)
            return authorIds.stream()
                    .map(authorMap::get)
                    .collect(Collectors.toList());
        });
    }
}
