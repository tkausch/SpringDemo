package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DataInitializer {

    @Bean
    ApplicationRunner initBooks(BookRepository repository) {
        return args -> {
            repository.save(new Book(null, "Clean Code", "Robert Martin"));
            repository.save(new Book(null, "Spring in Action", "Craig Walls"));
        };
    }
}
