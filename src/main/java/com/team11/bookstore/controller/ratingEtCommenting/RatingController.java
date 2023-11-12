package com.team11.bookstore.controller.ratingEtCommenting;

import com.team11.bookstore.model.M_Ratings;
import com.team11.bookstore.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/rate")
    public ResponseEntity<String> createRating(@RequestBody M_Ratings rating) {
        // Validate the rating value
        if (rating.getRatingValue() < 1 || rating.getRatingValue() > 5) {
            return new ResponseEntity<>("Invalid rating value.", HttpStatus.BAD_REQUEST);
        }

        // Set the current timestamp
        rating.setCreated_at(new Timestamp(new Date().getTime()));

        // Save the rating
        ratingService.createRating(rating);

        return new ResponseEntity<>("Rating created successfully.", HttpStatus.OK);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookAverageRating(@PathVariable Integer bookId) {
        Double averageRating = ratingService.getAverageRatingByBookId(bookId);
        if (averageRating == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Book not found or no ratings available.");
        }
        return ResponseEntity.ok(averageRating);
    }
}