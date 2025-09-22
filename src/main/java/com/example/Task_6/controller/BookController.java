package com.example.Task_6.controller;

import com.example.Task_6.DTOs.Book;
import com.example.Task_6.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/library/api")
@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> AllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book OnlyOneBook(@PathVariable int id){
        return bookService.getById(id);
    }

    @PostMapping
    public Book MakingBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public Book DeleteBook(@PathVariable int id){
        return bookService.removeBook(id);
    }

    @PutMapping("/{id}")
    public Book UpdateBook(@PathVariable int id, @RequestBody Book book){
        return bookService.modify(id,book);
    }
}
