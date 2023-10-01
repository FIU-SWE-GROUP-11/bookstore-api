package com.team11.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team11.bookstore.repository.BookRepository;
import com.team11.bookstore.model.M_Book;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<M_Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
