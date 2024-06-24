package com.example.rest_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.rest_service.model.Book;
import com.example.rest_service.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean deleteBook(Long id) {
        try{
            Book book = bookRepository.findById(id).orElseThrow();
            bookRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Book updateBook(Long id, Book book) {
        Book updatedBook = bookRepository.findById(id).orElse(null);
        if (updatedBook != null) {
            updatedBook.setTitle(book.getTitle() != null ? book.getTitle() : updatedBook.getTitle());
            updatedBook.setAuthor(book.getAuthor() != null ? book.getAuthor() : updatedBook.getAuthor());
            updatedBook.setPages(book.getPages() != 0 ? book.getPages() : updatedBook.getPages());
            bookRepository.save(updatedBook);
            return updatedBook;
        }

        return updatedBook;
    }
}
