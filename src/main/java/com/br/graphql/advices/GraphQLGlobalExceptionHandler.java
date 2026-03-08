package com.br.graphql.advices;

import com.br.graphql.exceptions.GraphQLEntityNotFoundException;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLGlobalExceptionHandler {

    @GraphQlExceptionHandler(GraphQLEntityNotFoundException.class)
    public GraphQLError handleNotFound(GraphQLEntityNotFoundException ex) {
        return GraphQLError.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(ex.getMessage())
                .build();
    }
}
