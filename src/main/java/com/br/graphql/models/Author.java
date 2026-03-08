package com.br.graphql.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    protected Author() {}

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Author author)) return false;

        return getId() == author.getId()
                && Objects.equals(getFirstName(), author.getFirstName())
                && Objects.equals(getLastName(), author.getLastName());
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(getId());
        result = 31 * result + Objects.hashCode(getFirstName());
        result = 31 * result + Objects.hashCode(getLastName());
        return result;
    }
}
