package com.br.graphql.service.dtos.inputs;

import java.util.Set;

public record BooksFilter(Set<Integer> genres, Integer maxPages) {}
