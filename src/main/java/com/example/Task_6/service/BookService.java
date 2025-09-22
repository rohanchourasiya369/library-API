package com.example.Task_6.service;

import com.example.Task_6.DTOs.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    Map<Integer, Book> hashbook = new HashMap<>();

    public List<Book> getAll(){   // It gives All Books
        return new ArrayList<>(hashbook.values());
    }

    public Book getById(int id){  // It gives Only One Book
        return hashbook.get(id);
    }

    public Book createBook(Book book){
        return hashbook.put(book.id(), book);
    }

    public Book removeBook(int id){
        return hashbook.remove(id);
    }

    public Book modify(int id,Book book){
        if(!hashbook.containsKey(id)){
            throw  new RuntimeException("Book with Id "+id+" was not found.");
        }
         hashbook.put(id,book);
        return book;

    }
}
