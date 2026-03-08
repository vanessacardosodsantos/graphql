INSERT INTO genre (name)
VALUES ('ACTION'),
       ('ADVENTURE'),
       ('COMEDY'),
       ('TERROR'),
       ('PROGRAMMING'),
       ('ROMANCE');

INSERT INTO author (first_name, last_name)
VALUES ('Joanne', 'Rowling'),
       ('Herman', 'Melville'),
       ('Anne', 'Rice');

INSERT INTO book (name, page_count, author_id)
VALUES ('Harry Potter and the Philosopher''s Stone', 223, 1),
       ('Harry Potter and the Chamber of Secrets', 300, 1),
       ('Moby Dick', 635, 2),
       ('Interview with the vampire', 371, 3);

INSERT INTO book_genres(book_id, genre_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (4, 4);