package com.example.rest_service.service;

import com.example.rest_service.model.Book;
import com.example.rest_service.repository.BookRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Pageable pageable;
    private List<Book> bookList;
    private Page<Book> bookPage;
    private Book book;

    @BeforeEach
    void setUp(){
        pageable = PageRequest.of(0,10);
        book = new Book("Book 1","Author 1",2000);
        bookList = Arrays.asList(
                book,
                new Book("Book 2","Author 2",2001)
        );
        bookPage = new PageImpl<>(bookList,pageable,bookList.size());
    }

    @Test
    void testGetAllBooks(){
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        Page<Book> result = bookService.getAllBooks(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).containsAll(bookList);
        verify(bookRepository,times(1)).findAll(pageable);


    }

    @Test
    void testGetOneBook(){
        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book));
        Book result = bookService.getBookById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Book 1");
    }
}
