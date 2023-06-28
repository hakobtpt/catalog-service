package com.catalog.service.demo;

import com.catalog.service.persistence.entity.Book;
import com.catalog.service.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
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
        bookRepository.deleteAll();
        bookRepository.saveAll(List.of(
                Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90, "Polarsophia"),
                Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Polarsophia")
        ));
    }
}
