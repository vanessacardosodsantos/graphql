package com.br.graphql.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int pageCount;

    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private final Set<Genre> genres = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    protected Book() {}

    public Book(String name, int pageCount, List<Genre> genres, Author author) {
        this.name = name;
        this.pageCount = pageCount;
        this.author = author;

        this.genres.addAll(genres);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;

        return getId() == book.getId()
                && getPageCount() == book.getPageCount()
                && Objects.equals(getName(), book.getName())
                && Objects.equals(getGenres(), book.getGenres())
                && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(getId());
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + getPageCount();
        result = 31 * result + Objects.hashCode(getGenres());
        result = 31 * result + Objects.hashCode(getAuthor());
        return result;
    }

    public void updateGenres(List<Genre> genres) {
        this.genres.clear();
        this.genres.addAll(genres);
    }
}
