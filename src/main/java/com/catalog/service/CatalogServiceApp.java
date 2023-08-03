package com.catalog.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CatalogServiceApp {
    public static void main(String[] args) {
//        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
        SpringApplication.run(CatalogServiceApp.class, args);
    }
}
