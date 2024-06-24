package com.example.rest_service.controller;

import com.example.rest_service.repository.BookRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.h2.util.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rest_service.model.Book;
import com.example.rest_service.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id,book);
    }

    @DeleteMapping("/{id}")
    public Map<String,Object> deleteBook(@PathVariable Long id) {
        boolean res = bookService.deleteBook(id);
        Map<String,Object> map = new HashMap<>();
        if(res){
            map.put("status","Success");
            map.put("id",id);
        }else{
            map.put("status","Error");
            map.put("message","Unable to delete book");
        }
        return map;
    }
}
