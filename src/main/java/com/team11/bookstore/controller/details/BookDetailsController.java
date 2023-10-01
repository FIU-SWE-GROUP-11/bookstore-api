package com.team11.bookstore.controller.details;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book/{id}")
public class BookDetailsController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<M_Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    // Other mappings for book browsing operations
}
