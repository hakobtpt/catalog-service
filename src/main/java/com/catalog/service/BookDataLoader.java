package com.catalog.service;

import com.catalog.service.persistence.entity.Book;
import com.catalog.service.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class BookDataLoader {

    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        List.of(
                new Book("1234567892", "Polar Journey", "Iorek Polarson", 12.90),
                new Book("1234567891", "Northern Lights", "Lyra Silverstar", 9.90)
        ).forEach(bookRepository::save);
    }
}
