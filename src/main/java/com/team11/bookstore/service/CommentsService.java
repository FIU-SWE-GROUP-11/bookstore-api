package com.team11.bookstore.service;

import com.team11.bookstore.model.M_Comments;
import com.team11.bookstore.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public M_Comments addComment(M_Comments comment) {
        return commentsRepository.save(comment);
    }
    public List<String> getCommentsByBookId(Integer bookId) {
        return commentsRepository.findCommentsByBookId(bookId);
    }
}