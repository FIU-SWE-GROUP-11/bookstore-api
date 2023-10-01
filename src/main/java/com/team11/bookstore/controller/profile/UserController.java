package com.yourpackage.controller;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.service.BookService;
import com.team11.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<M_User> getAllUsers() {
        return userService.getAllUsers();
    }
}

