package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<M_Comments, Integer> {
    // Custom query methods
}
