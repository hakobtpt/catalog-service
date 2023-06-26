package com.catalog.service.persistence.repository;

import com.catalog.service.persistence.entity.Book;

import java.util.Optional;

public interface BookRepository {

    Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    Book save(Book book);

    void deleteByIsbn(String isbn);
}
