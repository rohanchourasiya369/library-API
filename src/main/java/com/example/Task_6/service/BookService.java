package com.example.Task_6.service;

import com.example.Task_6.entity.Book;
import com.example.Task_6.exception.BookNotFoundException;
import com.example.Task_6.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book findById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    public Book deleteById(Long id) throws BookNotFoundException {
        Book book = findById(id);
        bookRepository.deleteById(id);
        return book;
    }

    public Book modifyBook(Book book, Long id) throws BookNotFoundException {
        Book bookUpdate = findById(id);
        bookUpdate.setTitle(book.getTitle());
        bookUpdate.setAuthor(book.getAuthor());
        bookUpdate.setAvailable(book.isAvailable());
        return bookRepository.save(bookUpdate);
    }
}
