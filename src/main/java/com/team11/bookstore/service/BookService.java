package com.team11.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team11.bookstore.repository.BookRepository;
import com.team11.bookstore.model.M_Book;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<M_Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }

    // Other mappings for book browsing operations
    public List<M_Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<M_Book> findByCopiesSold (Integer copiesSold) {
        List<M_Book> allBooks = bookRepository.findAll();
        allBooks.sort(Comparator.comparing(M_Book::getCopiesSold).reversed());
        return allBooks.subList(0, 10);
    }
  
    public boolean bookExists(Integer bookId) {
        return bookRepository.existsById(bookId);
    }

    public List<M_Book> findBooksByRating(Integer rating) {
        return bookRepository.findBooksByRating(rating);
    }

    public void applyDiscountToBooksByPublisher(String publisher, BigDecimal discountPercent) {
        List<M_Book> booksToDiscount = bookRepository.findByPublisher(publisher);
        for (M_Book book : booksToDiscount) {
            BigDecimal currentPrice = book.getPrice();
            BigDecimal discountAmount = currentPrice.multiply(discountPercent);
            BigDecimal discountedPrice = currentPrice.subtract(discountAmount);

            if (discountedPrice.compareTo(BigDecimal.ZERO) < 0) {
                discountedPrice = BigDecimal.ZERO;
            }

            book.setPrice(discountedPrice);
            bookRepository.save(book);
        }
    }

    public void removeDiscountFromBooksByPublisher(String publisher, BigDecimal discountPercent) {
        List<M_Book> booksToDiscount = bookRepository.findByPublisher(publisher);
        for (M_Book book : booksToDiscount) {
            BigDecimal currentPrice = book.getPrice();
            BigDecimal originalPrice = currentPrice.divide(BigDecimal.ONE.subtract(discountPercent), 2, RoundingMode.HALF_UP);
            book.setPrice(originalPrice);
            bookRepository.save(book);
        }
    }
}


