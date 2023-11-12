package com.team11.bookstore.controller.wishlist;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_WishList;
import com.team11.bookstore.model.M_WishListItems;
import com.team11.bookstore.service.WishlistService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishListService;

    @PostMapping("/create")
    public ResponseEntity<?> createWishlist(@RequestParam Integer userId, @RequestParam String wishlistName) {
        try {
            wishListService.createWishlist(userId, wishlistName);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBookToWishlist(@RequestParam Integer bookId, @RequestParam Integer wishlistId) {
//        wishListService.addBookToWishlist(bookId, wishlistId);
        try{
            wishListService.addBookToWishlist(bookId,wishlistId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<?> removeBookFromWishlist(@RequestParam Integer bookId, @RequestParam Integer wishlistId){
//        wishListService.removeBookFromWishlist(bookId, wishlistId);
        try {
            wishListService.removeBookFromWishlist(bookId, wishlistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooksInWishList(@RequestParam Integer wishlistId){
        try {
            List<M_Book> booksInWishlist = wishListService.getAllBooksInWishList(wishlistId);
            return new ResponseEntity<>(booksInWishlist, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            String errorMessage = e.getMessage();
            return new ResponseEntity<>(errorMessage ,HttpStatus.NOT_FOUND);
        }
    }
}
