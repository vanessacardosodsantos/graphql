package com.br.graphql.exceptions;

public class GraphQLEntityNotFoundException extends RuntimeException {

    public GraphQLEntityNotFoundException(String message) {
        super(message);
    }
}
