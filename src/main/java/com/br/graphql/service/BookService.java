package com.br.graphql.service;

import com.br.graphql.exceptions.GraphQLEntityNotFoundException;
import com.br.graphql.models.Author;
import com.br.graphql.models.Book;
import com.br.graphql.models.BookGenre;
import com.br.graphql.models.Genre;
import com.br.graphql.repository.BookRepository;
import com.br.graphql.service.dtos.inputs.BookInput;
import com.br.graphql.service.dtos.inputs.BooksFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.br.graphql.business.BookSpecifications.containsGenre;
import static com.br.graphql.business.BookSpecifications.isPagesLessEqualThen;

@Service
public class BookService {

    private final BookRepository repository;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookService(BookRepository repository, AuthorService authorService, GenreService genreService) {
        this.repository = repository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    public Book findBookById(long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Book createBook(BookInput book) {
        Author author = authorService.findAuthor(book.authorId());

        if(author == null)
            throw new GraphQLEntityNotFoundException("Author not found");

        List<Genre> genres = genreService.findAllByIds(book.genres());

        if(genres.size() != book.genres().size()) {
            throw new GraphQLEntityNotFoundException("An genre was not found");
        }

        Book newBook = new Book(
                book.name(),
                book.pageCount(),
                genres,
                author
        );

        return repository.save(newBook);
    }

    public Book updateBook(long id, BookInput book) {
        Optional<Book> optional = repository.findById(id);

        if(optional.isEmpty())
            throw new GraphQLEntityNotFoundException("Book not found");

        Book savedBook = optional.get();

        /*
            Sem validação no update apenas para facilitar, a ideia aqui é apenas mostrar
            o GraphQL funcionando
         */
        savedBook.setName(book.name());
        savedBook.setPageCount(book.pageCount());

        List<Genre> genres = genreService.findAllByIds(book.genres());

        if(genres.size() != book.genres().size()) {
            throw new GraphQLEntityNotFoundException("An genre was not found");
        }

        savedBook.updateGenres(genres);

        Author author = authorService.findAuthor(book.authorId());

        if(author == null)
            throw new GraphQLEntityNotFoundException("Author not found");

        savedBook.setAuthor(author);

        return repository.save(savedBook);
    }

    public boolean deleteBook(long id) {
        if(!repository.existsById(id))
            throw new GraphQLEntityNotFoundException("Book not found");

        repository.deleteById(id);

        return true;
    }

    public List<Book> findBooks(BooksFilter filter) {
        Specification<Book> spec = Specification.where(isPagesLessEqualThen(filter.maxPages()))
                .and(containsGenre(filter.genres()));

        return repository.findAll(spec);
    }

    public List<Book> findBookByAuthor(Author author) {
        return repository.findByAuthorId(author.getId());
    }

    public List<BookGenre> findGenresByIdsBooks(List<Long> books) {
        return genreService.findAllByBookIdIn(books);
    }
}
