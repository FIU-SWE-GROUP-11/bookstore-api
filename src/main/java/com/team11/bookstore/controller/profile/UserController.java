package com.team11.bookstore.controller.profile;

import com.team11.bookstore.customExceptions.CustomExceptions;
import com.team11.bookstore.model.M_CreditCard;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody M_User newUser) {
        try {
            if (!userService.userExistsByUsername(newUser.getUsername(), false)) {
                if (newUser.getUsername() == null || newUser.getPassword() == null) {
                    throw new CustomExceptions.MissingSignUpFieldsException("Username and password are required.");
                }
                userService.saveUser(newUser);
                return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
            }
            String errorMessage = "Username \"" + newUser.getUsername() + "\" is taken.";
            throw new CustomExceptions.UsernameAlreadyExistsException(errorMessage);
        }
        catch(CustomExceptions.UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(CustomExceptions.MissingSignUpFieldsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. Please try again.");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            if (userService.userExistsByUsername(username, false)) {
                return new ResponseEntity<>(userService.getUser(username, false), HttpStatus.OK);
            }
            String errorMessage = "User \"" + username + "\" does not exist.";
            throw new CustomExceptions.UserDoesNotExistException(errorMessage);
        }
        catch(CustomExceptions.UserDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. Please try again.");
        }
    }

    @PostMapping(path = "/add-credit-card/{username}")
    public ResponseEntity<String> addCreditCard(@PathVariable String username, @RequestBody M_CreditCard newCard) {
        try {
            if (userService.userExistsByUsername(username, false)) {
                if (newCard.getCardName() == null || newCard.getCardNumber() == null || newCard.getCvv() == null || newCard.getExpirationDate() == null) {
                    throw new CustomExceptions.MissingCreditCardFieldsException("Cardholder name, card number, CVV, and expiration date are required.");
                }
                newCard.setUser(userService.getUser(username, false));
                userService.saveCreditCard(newCard);
                String successMessage = "Successfully added credit card for \"" + userService.getUser(username, false).getUsername() + "\".";
                return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
            }
            String errorMessage = "User \"" + username + "\" does not exist.";
            throw new CustomExceptions.UserDoesNotExistException(errorMessage);
        }
        catch (CustomExceptions.UserDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (CustomExceptions.MissingCreditCardFieldsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. Please try again.");
        }
    }

    @PatchMapping(path = "/update/{username}")
    public ResponseEntity<String> updateUserInfo(@PathVariable String username, @RequestBody Map<String, String> updatedFields) {
        try {
            if (!userService.userExistsByUsername(username, false)) {
                String errorMessage = "User \"" + username + "\" does not exist.";
                throw new CustomExceptions.UserDoesNotExistException(errorMessage);
            }
            M_User updatedUser = userService.getUser(username, false);
            if (updatedFields.containsKey("emailAddress")) {
                if (updatedUser.getEmail() != null) {
                    throw new CustomExceptions.CannotChangeEmailException("Cannot change email address.");
                }
                else {
                    updatedUser.setEmail(updatedFields.get("emailAddress"));
                }
            }
            if (updatedFields.containsKey("name")) {
                updatedUser.setName(updatedFields.get("name"));
            }
            if (updatedFields.containsKey("password")) {
                updatedUser.setPassword(updatedFields.get("password"));
            }
            if (updatedFields.containsKey("homeAddress")) {
                updatedUser.setHomeAddress(updatedFields.get("homeAddress"));
            }
            if (updatedFields.containsKey("username")) {
                String newUsername = updatedFields.get("username");
                if (!username.equalsIgnoreCase(newUsername) && userService.userExistsByUsername(newUsername, false)) {
                    String errorMessage = "Username \"" + newUsername + "\" is taken.";
                    throw new CustomExceptions.UsernameAlreadyExistsException(errorMessage);
                }
                updatedUser.setUsername(newUsername);
                username = newUsername;
            }
            userService.saveUser(updatedUser);
            String successMessage = "Successfully updated account information for \"" + userService.getUser(username, false).getUsername() + "\".";
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }
        catch(CustomExceptions.UserDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(CustomExceptions.UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(CustomExceptions.CannotChangeEmailException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. Please try again.");
        }
    }
}

