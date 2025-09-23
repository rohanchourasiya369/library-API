package com.example.Task_6.advice;

import com.example.Task_6.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String,String> expMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
          expMap.put(err.getField(),err.getDefaultMessage()));
        return expMap;
    }


    @ExceptionHandler(BookNotFoundException.class)
    public Map<String,String> handleBookNotFound(BookNotFoundException ex){
        Map<String,String> bookExp = new HashMap<>();
        bookExp.put("ErrorMessage", ex.getMessage());
        return bookExp;

    }
}
