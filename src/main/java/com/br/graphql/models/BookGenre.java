package com.br.graphql.models;

import jakarta.persistence.*;

@Entity
@Table(name = "book_genres")
public class BookGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_id")
    private long bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    protected BookGenre() {}

    public long getBookId() {
        return bookId;
    }

    public Genre getGenre() {
        return genre;
    }

    public long getId() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof BookGenre bookGenre)) return false;

        return getId() == bookGenre.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(getId());
    }
}
