package com.catalog.service.persistence.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class BookValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        //GIVEN
        var book = new Book("1234567890", "Title", "Author", 9.90);

        //WHEN
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //THEN
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        //GIVEN
        var book = new Book("", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //WHEN
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());

        //THEN
        assertThat(violations).hasSize(2);
        assertThat(constraintViolationMessages)
                .contains("The book ISBN must be defined.")
                .contains("The ISBN format must be valid.");
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        //GIVEN
        var book = new Book("a234567890", "Title", "Author", 9.90);

        //WHEN
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //THEN
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid.");
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails() {
        //GIVEN
        var book = new Book("1234567890", "", "Author", 9.90);

        //WHEN
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //THEN
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book title must be defined.");
    }

    @Test
    void whenAuthorIsNotDefinedThenValidationFails() {
        //GIVEN
        var book = new Book("1234567890", "Title", "", 9.90);

        //WHEN
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //THEN
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book author must be defined.");
    }

    @Test
    void whenPriceIsNotDefinedThenValidationFails() {
        //GIVEN
        var book = new Book("1234567890", "Title", "Author", null);

        //WHEN
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //THEN
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be defined.");
    }

    @Test
    void whenPriceDefinedButZeroThenValidationFails() {
        //GIVEN
        var book = new Book("1234567890", "Title", "Author", 0.0);

        //WHEN
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //THEn
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }

    @Test
    void whenPriceDefinedButNegativeThenValidationFails() {
        //GIVEN
        var book = new Book("1234567890", "Title", "Author", -9.90);

        //WHEN
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        //THEN
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }
}