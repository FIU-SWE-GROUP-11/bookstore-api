package com.team11.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team11.bookstore.repository.AuthorRepository;
import com.team11.bookstore.model.M_Author;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<M_Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
