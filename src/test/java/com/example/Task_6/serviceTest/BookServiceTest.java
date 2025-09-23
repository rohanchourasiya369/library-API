package com.example.Task_6.serviceTest;

import com.example.Task_6.entity.Book;
import com.example.Task_6.exception.BookNotFoundException;
import com.example.Task_6.repository.BookRepository;
import com.example.Task_6.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    private BookRepository repository;
    private BookService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(BookRepository.class);
        service = new BookService(repository); // using constructor injection
    }

    @Test
    void testAddBook() {
        Book book = new Book(null, "Java Basics", "James Gosling", true);

        when(repository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        Book saved = service.saveBook(book);

        AssertionErrors.assertEquals("Title mismatch", "Java Basics", saved.getTitle());
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> service.findById(1L));
    }
}
