package com.example.Task_6.controller;

import com.example.Task_6.dto.BookDTO;
import com.example.Task_6.dto.BookSummary;
import com.example.Task_6.exception.BookNotFoundException;
import com.example.Task_6.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/library-api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO dto){
       return new ResponseEntity<>(bookService.addBook(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws BookNotFoundException {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO dto, @PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.modifyBook(dto,id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookSummary>> availableBooks() {
        return ResponseEntity.ok(bookService.availableBooks());
    }
}
