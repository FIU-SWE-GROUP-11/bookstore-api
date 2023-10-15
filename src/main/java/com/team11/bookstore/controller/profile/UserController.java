package com.team11.bookstore.controller.profile;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.service.BookService;
import com.team11.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody M_User newUser) {
        userService.saveUser(newUser);
    }

    @GetMapping("/{username}")
    public M_User getUserByUsername(@PathVariable String username) {
        return userService.getUser(username);
    }
}

