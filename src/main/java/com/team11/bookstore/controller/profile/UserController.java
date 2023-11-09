package com.team11.bookstore.controller.profile;

import com.team11.bookstore.model.M_CreditCard;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        if (!userService.userExistsByUsername(newUser.getUsername(), false)) {
            if (newUser.getUsername() == null || newUser.getPassword() == null) {
                return new ResponseEntity<>("Username and password are required.", HttpStatus.BAD_REQUEST);
            }
            userService.saveUser(newUser);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        }
        String errorMessage = "Username \"" + newUser.getUsername() + "\" is taken.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        if (userService.userExistsByUsername(username, false)) {
            return new ResponseEntity<>(userService.getUser(username, false), HttpStatus.OK);
        }
        String errorMessage = "User \"" + username + "\" does not exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/add-credit-card/{username}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCreditCard(@PathVariable String username, @RequestBody M_CreditCard newCard) {
        if (userService.userExistsByUsername(username, false)) {
            if (newCard.getCardName() == null || newCard.getCardNumber() == null || newCard.getCvv() == null || newCard.getExpirationDate() == null) {
                return new ResponseEntity<>("Cardholder name, card number, CVV, and expiration date are required.", HttpStatus.BAD_REQUEST);
            }
            newCard.setUser(userService.getUser(username, false));
            userService.saveCreditCard(newCard);
            String successMessage = "Successfully added credit card for \"" + userService.getUser(username, false).getUsername() + "\".";
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        }
        String errorMessage = "User \"" + username + "\" does not exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "/update/{username}")
    public ResponseEntity<String> updateUserInfo(@PathVariable String username, @RequestBody Map<String, String> updatedFields) {
        if (!userService.userExistsByUsername(username, false)) {
            String errorMessage = "User \"" + username + "\" does not exist.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        if (updatedFields.containsKey("email")){
            return new ResponseEntity<>("Cannot change email address.", HttpStatus.BAD_REQUEST);
        }
        M_User updatedUser = userService.getUser(username, false);
        if (updatedFields.containsKey("name")){
            updatedUser.setName(updatedFields.get("name"));
        }
        if (updatedFields.containsKey("password")){
            updatedUser.setPassword(updatedFields.get("password"));
        }
        if (updatedFields.containsKey("homeAddress")){
            updatedUser.setHomeAddress(updatedFields.get("homeAddress"));
        }
        if (updatedFields.containsKey("username")){
            String newUsername = updatedFields.get("username");
            if (!username.equalsIgnoreCase(newUsername) && userService.userExistsByUsername(newUsername, false)) {
                String errorMessage = "Username \"" + newUsername + "\" is taken.";
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
            updatedUser.setUsername(newUsername);
            username = newUsername;
        }
        userService.saveUser(updatedUser);
        String successMessage = "Successfully updated account information for \"" + userService.getUser(username, false).getUsername() + "\".";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}

