package com.catalog.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    ProblemDetail bookNotFoundHandler(BookNotFoundException ex) {
        var problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Book Not Found");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    ErrorResponse bookAlreadyExistsHandler(BookAlreadyExistsException ex) {
        return ErrorResponse.builder(ex, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage())
                .title("Book Already Exists")
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setProperty("timestamp", Instant.now());
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    problem.setProperty(fieldName, errorMessage != null ? errorMessage : "");
                });
        return problem;
    }
}
