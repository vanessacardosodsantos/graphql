package com.br.graphql.repository;

import com.br.graphql.models.Book;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = "author")
    List<Book> findAll(@NonNull Specification<Book> spec);

    @EntityGraph(attributePaths = "author")
    List<Book> findByAuthorId(long id);
}
