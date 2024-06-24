package com.example.rest_service.repository;

import com.example.rest_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



    @Repository
    public interface BookRepository extends JpaRepository<Book, Long> {
    }

