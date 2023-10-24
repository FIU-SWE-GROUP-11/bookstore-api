package com.team11.bookstore.controller.profile;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.service.BookService;
import com.team11.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> signup(@RequestBody M_User newUser) {
        M_User user = userService.getUser(newUser.getUsername());
        if (user == null) {
            if (newUser.getUsername() == null || newUser.getPassword() == null) {
                return new ResponseEntity<>("Username and password are required.", HttpStatus.BAD_REQUEST);
            }
            userService.saveUser(newUser);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Username is taken.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public M_User getUserByUsername(@PathVariable String username) {
        return userService.getUser(username);
    }
}

