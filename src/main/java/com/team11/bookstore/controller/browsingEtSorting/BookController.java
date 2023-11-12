package com.team11.bookstore.controller.browsingEtSorting;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<M_Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> findByGenre(@PathVariable String genre) {
        List<M_Book> bookGenre = bookService.findByGenre(genre);

        if (!bookGenre.isEmpty()) {
            return ResponseEntity.ok(bookGenre);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No " + genre + " books found.");
        }
    }


    @GetMapping("/top-sellers")
    public List<M_Book> findByCopiesSold(Integer copiesSold) {
        return bookService.findByCopiesSold(copiesSold);
    }

    @GetMapping("/by-rating/{rating}")
    public ResponseEntity<?> findBooksByRating(@PathVariable Integer rating) {
        List<M_Book> bookRating = bookService.findBooksByRating(rating);

        if (!bookRating.isEmpty()) {
            return ResponseEntity.ok(bookRating);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books rated " + rating + " found.");
        }
    }

    @PatchMapping("/apply-discount/{publisher}/{discountPercent}")
    public ResponseEntity<String> applyDiscountToBooksByPublisher(
            @PathVariable String publisher,
            @PathVariable BigDecimal discountPercent) {

        if (bookService.publisherExists(publisher)) {
            bookService.applyDiscountToBooksByPublisher(publisher, discountPercent);
            return ResponseEntity.ok("Discount applied to books under publisher \"" + publisher + "\".");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher \"" + publisher + "\" not found.");
        }
    }


    @PatchMapping("/remove-discount/{publisher}/{discountPercent}")
    public ResponseEntity<String> removeDiscountFromBooksByPublisher(
            @PathVariable String publisher,
            @PathVariable BigDecimal discountPercent) {

        if (bookService.publisherExists(publisher)) {
            bookService.removeDiscountFromBooksByPublisher(publisher, discountPercent);
            return ResponseEntity.ok("Discount removed from books under publisher \"" + publisher + "\".");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher \"" + publisher + "\" not found.");
        }
    }

    @GetMapping("/details/{bookId}")
    public ResponseEntity<?> getBookDetails(@PathVariable Integer bookId) {
        if (!bookService.bookExists(bookId)) {
            return new ResponseEntity<>("No book with ID " + bookId + " found.", HttpStatus.NOT_FOUND);
        }

        M_Book book = bookService.getBookDetails(bookId);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return new ResponseEntity<>("Book details for ID " + bookId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
