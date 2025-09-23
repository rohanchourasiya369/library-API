package com.example.Task_6.repositoryTest;

import com.example.Task_6.entity.Book;
import com.example.Task_6.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFindBook() {
        Book book = new Book(null,"Harry Poter", "J.k.Rolling", true);
        bookRepository.save(book);

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(1);
        assertThat(bookList.get(0).getTitle()).isEqualTo("Harry Poter");
    }
}
