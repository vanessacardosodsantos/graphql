package com.br.graphql.config;

import com.br.graphql.dataloader.AuthorDataLoader;
import com.br.graphql.models.Author;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.graphql.execution.DefaultBatchLoaderRegistry;

@Configuration
public class DataLoaderConfig {

    private final AuthorDataLoader authorDataLoader;

    public DataLoaderConfig(AuthorDataLoader authorDataLoader) {
        this.authorDataLoader = authorDataLoader;
    }

    @Bean
    public BatchLoaderRegistry batchLoaderRegistry() {
        BatchLoaderRegistry registry = new DefaultBatchLoaderRegistry();

        registry.forTypePair(Long.class, Author.class)
                .withName("authorLoader")
                .registerMappedBatchLoader((ids, _) -> authorDataLoader.load(ids));

        return registry;
    }


}
