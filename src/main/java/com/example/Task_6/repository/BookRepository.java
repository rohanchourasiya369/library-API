package com.example.Task_6.repository;

import com.example.Task_6.dto.BookDTO;
import com.example.Task_6.dto.BookSummary;
import com.example.Task_6.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT new com.example.Task_6.dto.BookDTO(b.id, b.title, b.author, b.available) FROM Book b")
    List<BookDTO> findAllBooksDTO();

    @Query("SELECT new com.example.Task_6.dto.BookSummary(b.id, b.title, b.author) FROM Book b WHERE b.available = true")
    List<BookSummary> findAvailableSummaries();

    @Query("SELECT new com.example.Task_6.dto.BookDTO(b.id, b.title, b.author, b.available) FROM Book b WHERE b.id = :id")
    BookDTO findBookDTOById(Long id);
}
