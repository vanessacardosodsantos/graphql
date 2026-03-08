package com.br.graphql.business;

import com.br.graphql.models.Book;
import com.br.graphql.models.Genre;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class BookSpecifications {

    public static Specification<Book> isPagesLessEqualThen(Integer maxPages) {
        return (root, _, cb) ->
                maxPages == null ? null : cb.lessThanOrEqualTo(root.get("pageCount"), maxPages);
    }

    public static Specification<Book> containsGenre(Set<Integer> genres) {
        return (root, query, _) -> {
            if (genres == null || genres.isEmpty())
                return null;

            Join<Book, Genre> joinGenres = root.join("genres", JoinType.INNER);

            return joinGenres.get("id").in(genres);
        };
    }
}
