package com.example.Task_6.service;

import com.example.Task_6.dto.BookDTO;
import com.example.Task_6.dto.BookSummary;
import com.example.Task_6.entity.Book;
import com.example.Task_6.exception.BookNotFoundException;
import com.example.Task_6.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private  BookRepository repository;

    public BookService(BookRepository bookRepository){
        this.repository = bookRepository;
    }

    // add Books
    public BookDTO addBook(BookDTO dto){
        Book book = new Book(dto.title(), dto.author(), dto.available());
        Book current = repository.save(book);
        return  new BookDTO(current.getId(),current.getTitle(), current.getAuthor(),current.isAvailable());
    }

    // GET all Books
    public List<BookDTO> findAllBooks(){
        return repository.findAllBooksDTO();
    }

    // find Books By id
    public BookDTO findBookById(Long id) throws BookNotFoundException {
        BookDTO dto =  repository.findBookDTOById(id);
        if (dto == null){
            throw  new BookNotFoundException(id);
        }
        return dto;
    }

    // delete Book By id
    public void delete(Long id) throws BookNotFoundException {
        Book book = repository.findById(id).orElseThrow( () -> new BookNotFoundException(id));
        repository.delete(book);
    }

    // update Book By id
    public BookDTO modifyBook(BookDTO dto, Long id) throws BookNotFoundException {
        Book existing = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        existing.setTitle(dto.title());
        existing.setAuthor(dto.author());
        Optional.ofNullable(dto.available()).ifPresent(existing::setAvailable);
         Book update = repository.save(existing);
         return  new BookDTO(update.getId(),update.getTitle(),update.getAuthor(),update.isAvailable());
    }

    // check Summary was available
    public List<BookSummary> availableBooks(){
//        return repository.findAll().stream().filter(Book :: isAvailable)
//        .map(book ->{
//            if(book instanceof  Book b){
//                return  new BookSummary(b.getId(),b.getTitle(),b.getAuthor());
//            }
//            return  null;
//        }).toList();
        return repository.findAvailableSummaries();
    }
}
