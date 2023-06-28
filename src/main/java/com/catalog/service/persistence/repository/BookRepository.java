package com.catalog.service.persistence.repository;

import com.catalog.service.persistence.entity.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    //Identifies an operation that will modify the database state
    @Modifying
    //Declares the query that Spring Data will use to implement the method
    @Query("delete from Book where isbn = :isbn")
    @Transactional
    void deleteByIsbn(String isbn);
}
