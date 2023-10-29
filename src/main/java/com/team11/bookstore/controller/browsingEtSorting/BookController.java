package com.team11.bookstore.controller.browsingEtSorting;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<M_Book> findByGenre(@PathVariable String genre) {
        return bookService.findByGenre(genre);
    }

    @GetMapping("/top-sellers")
    public List<M_Book> findByCopiesSold(Integer copiesSold) {
        return bookService.findByCopiesSold(copiesSold);
    }

    @GetMapping("/by-rating/{rating}")
    public List<M_Book> findBooksByRating(@PathVariable Integer rating) {
        return bookService.findBooksByRating(rating);
    }

    @PatchMapping("/apply-discount/{publisher}/{discountPercent}")
    public void applyDiscountToBooksByPublisher(
            @PathVariable String publisher,
            @PathVariable BigDecimal discountPercent) {
        bookService.applyDiscountToBooksByPublisher(publisher, discountPercent);
    }

    @PatchMapping("/remove-discount/{publisher}/{discountPercent}")
    public void removeDiscountFromBooksByPublisher(
            @PathVariable String publisher,
            @PathVariable BigDecimal discountPercent) {
        bookService.removeDiscountFromBooksByPublisher(publisher, discountPercent);
    }
    // Other mappings for book browsing operations
}
