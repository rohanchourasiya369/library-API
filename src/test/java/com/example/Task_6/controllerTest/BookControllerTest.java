package com.example.Task_6.controllerTest;

import com.example.Task_6.entity.Book;
import com.example.Task_6.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
        repo.save(new Book("Test Book", "Tester", true));
    }

    @Test
    void shouldReturnAvailableBooks() throws Exception {
        mockMvc.perform(get("/library-api/books/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    @Test
    void shouldAddBook() throws Exception {
        mockMvc.perform(post("/library-api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Book\",\"author\":\"Author\",\"available\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Book"));
    }

    @Test
    void shouldGetBookById() throws Exception {
        Long id = repo.findAll().get(0).getId();
        mockMvc.perform(get("/library-api/books/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        Long id = repo.findAll().get(0).getId();
        mockMvc.perform(put("/library-api/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated\",\"author\":\"Author\",\"available\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        Long id = repo.findAll().get(0).getId();
        mockMvc.perform(delete("/library-api/books/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        mockMvc.perform(get("/library-api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }
}
