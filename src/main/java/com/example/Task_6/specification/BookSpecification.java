package com.example.Task_6.specification;

import com.example.Task_6.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> hasTitle(String title){
        return  ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("title"),title));
    }

    public static Specification<Book> hasAuthor(String author){
        return (((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("author"),author)));
    }
}
