//package com.catalog.service.service;
//
//import com.catalog.service.exception.BookAlreadyExistsException;
//import com.catalog.service.exception.BookNotFoundException;
//import com.catalog.service.persistence.entity.Book;
//import com.catalog.service.persistence.repository.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.BDDMockito.given;
//
//@ExtendWith(MockitoExtension.class)
//class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Test
//    void whenBookToCreateAlreadyExistsThenThrows() {
//        //GIVEN
//        var bookIsbn = "1234561232";
//        var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
//        given(bookRepository.existsByIsbn(bookIsbn)).willReturn(true);
//
//        //WHEN
//        var actual = assertThatThrownBy(() -> bookService.addBookToCatalog(bookToCreate));
//
//        //THEN
//        actual.isInstanceOf(BookAlreadyExistsException.class)
//                .hasMessage("A book with ISBN " + bookIsbn + " already exists.");
//    }
//
//    @Test
//    void whenBookToReadDoesNotExistThenThrows() {
//        //GIVEN
//        var bookIsbn = "1234561232";
//        given(bookRepository.findByIsbn(bookIsbn)).willReturn(Optional.empty());
//
//        //WHEN
//        var actual = assertThatThrownBy(() -> bookService.viewBookDetails(bookIsbn));
//
//        //THEN
//        actual.isInstanceOf(BookNotFoundException.class)
//                .hasMessage("The book with ISBN " + bookIsbn + " was not found.");
//    }
//}