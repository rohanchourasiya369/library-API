package com.example.Task_6.controller;

import com.example.Task_6.dto.BookDTO;
import com.example.Task_6.entity.Book;
import com.example.Task_6.exception.BookNotFoundException;
import com.example.Task_6.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library-api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll(){
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO){
        Book book = new Book(null, bookDTO.title(), bookDTO.author(), true);
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) throws BookNotFoundException {
        Book book = new Book(id, bookDTO.title(), bookDTO.author(), true);
        return ResponseEntity.ok(bookService.modifyBook(book, id));
    }
}
