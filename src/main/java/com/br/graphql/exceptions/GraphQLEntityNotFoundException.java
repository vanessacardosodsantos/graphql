package com.br.graphql.exceptions;

public class GraphQLEntityNotFoundException extends RuntimeException {

    private final String message;

    public GraphQLEntityNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
