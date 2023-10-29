package com.team11.bookstore.controller.ratingEtCommenting;

import com.team11.bookstore.model.M_Comments;
import com.team11.bookstore.service.BookService;
import com.team11.bookstore.service.UserService;
import com.team11.bookstore.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody M_Comments comment) {
        // Check if user and book exist
        if (!userService.userExists(comment.getUser().getId())) {
            return new ResponseEntity<>("Invalid user ID.", HttpStatus.BAD_REQUEST);
        }
        if (!bookService.bookExists(comment.getBook().getBookID())) {
            return new ResponseEntity<>("Invalid book ID.", HttpStatus.BAD_REQUEST);
        }

        // Check if comment is empty or null
        if (comment.getCommentDescription() == null || comment.getCommentDescription().trim().isEmpty()) {
            return new ResponseEntity<>("Comment field cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        comment.setCreated_at(new Timestamp(System.currentTimeMillis()));
        commentsService.addComment(comment);
        return new ResponseEntity<>("Comment added successfully.", HttpStatus.CREATED);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<String> getCommentsByBookId(@PathVariable Integer bookId) {
        // Check if the book exists
        if (!bookService.bookExists(bookId)) {
            return new ResponseEntity<>("No book found with ID " + bookId, HttpStatus.NOT_FOUND);
        }

        List<String> comments = commentsService.getCommentsByBookId(bookId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>("No comments found for the book with ID " + bookId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comments.toString(), HttpStatus.OK);
    }
}


