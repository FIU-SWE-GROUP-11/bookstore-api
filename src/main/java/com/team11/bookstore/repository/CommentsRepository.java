package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<M_Comments, Integer> {

    @Query("SELECT c.commentDescription FROM M_Comments c WHERE c.book.bookID = :bookId")
    List<String> findCommentsByBookId(@Param("bookId") Integer bookId);
}