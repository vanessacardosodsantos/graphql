package com.br.graphql.repository;

import com.br.graphql.models.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookGenreRepository extends JpaRepository<BookGenre, Long> {

    @Query("""
        SELECT bg FROM BookGenre bg
        JOIN FETCH bg.genre          
        WHERE bg.bookId IN :bookIds
        """)
    List<BookGenre> findAllByBookIdIn(@Param("bookIds") List<Long> bookIds);

}
