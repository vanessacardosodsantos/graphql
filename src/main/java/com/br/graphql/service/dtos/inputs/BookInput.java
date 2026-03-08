package com.br.graphql.service.dtos.inputs;

import java.util.Set;

public record BookInput(String name,
                        Integer pageCount,
                        Set<Integer> genres,
                        long authorId) {
}
