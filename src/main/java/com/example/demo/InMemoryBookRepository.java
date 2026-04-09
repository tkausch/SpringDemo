package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("in-memory")
public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream().filter(b -> b.id.equals(id)).findFirst();
    }

    @Override
    public Book save(Book book) {
        if (book.id == null) {
            book.id = idSequence.getAndIncrement();
        }
        books.add(book);
        return book;
    }
}
