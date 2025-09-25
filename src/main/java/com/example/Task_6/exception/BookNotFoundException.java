package com.example.Task_6.exception;

public class BookNotFoundException  extends  Exception{
    public BookNotFoundException(Long id) {
        super("Book not found with id : "+id);
    }
}
