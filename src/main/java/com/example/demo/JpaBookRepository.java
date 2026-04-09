package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface JpaBookRepository extends JpaRepository<Book, Long>, BookRepository {
}
