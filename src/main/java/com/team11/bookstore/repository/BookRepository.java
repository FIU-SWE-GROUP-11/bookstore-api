package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<M_Book, Integer> {
    // Custom query methods
}
