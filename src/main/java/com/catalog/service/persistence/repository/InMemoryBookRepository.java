package com.catalog.service.persistence.repository;

import com.catalog.service.persistence.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryBookRepository implements BookRepository {

    private static final Map<String, Book> BOOK_MAP = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return BOOK_MAP.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn)
                ? Optional.of(BOOK_MAP.get(isbn))
                : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return BOOK_MAP.get(isbn) != null;
    }

    @Override
    public Book save(Book book) {
        BOOK_MAP.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        BOOK_MAP.remove(isbn);
    }
}
