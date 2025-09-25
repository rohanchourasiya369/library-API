package com.example.Task_6.serviceTest;

import com.example.Task_6.dto.BookDTO;
import com.example.Task_6.dto.BookSummary;
import com.example.Task_6.entity.Book;
import com.example.Task_6.exception.BookNotFoundException;
import com.example.Task_6.repository.BookRepository;
import com.example.Task_6.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private final BookRepository repo = mock(BookRepository.class);
    private final BookService service = new BookService(repo);

    @Test
    void testAddBook() {
        BookDTO dto = new BookDTO(null, "Title", "Author", true);
        when(repo.save(any(Book.class))).thenReturn(new Book("Title", "Author", true));

        BookDTO result = service.addBook(dto);

        assertEquals("Title", result.title());
        verify(repo, times(1)).save(any(Book.class));
    }

    @Test
    void testFindById_Found() throws BookNotFoundException {
        BookDTO dto = new BookDTO(1L, "Title", "Author", true);
        when(repo.findBookDTOById(1L)).thenReturn(dto);

        BookDTO result = service.findBookById(1L);
        assertEquals("Title", result.title());
    }

    @Test
    void testFindById_NotFound() {
        when(repo.findBookDTOById(1L)).thenReturn(null);
        assertThrows(BookNotFoundException.class, () -> service.findBookById(1L));
    }

    @Test
    void testModifyBook() throws BookNotFoundException {
        Book existing = new Book("Old", "Old Author", true);
        existing.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));

        BookDTO updateDTO = new BookDTO(null, "New", "New Author", false);
        BookDTO result = service.modifyBook(updateDTO, 1L);

        assertEquals("New", result.title());
        assertEquals("New Author", result.author());
        assertFalse(result.available());
    }

    @Test
    void testDeleteBook() throws BookNotFoundException {
        Book book = new Book("Delete", "Author", true);
        book.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(repo).delete(book);

        service.delete(1L);
        verify(repo, times(1)).delete(book);
    }

    @Test
    void testAvailableBooks() {
        List<BookSummary> summaries = List.of(new BookSummary(1L, "Title", "Author"));
        when(repo.findAvailableSummaries()).thenReturn(summaries);

        List<BookSummary> result = service.availableBooks();
        assertEquals(1, result.size());
        assertEquals("Title", result.get(0).title());
    }
}
