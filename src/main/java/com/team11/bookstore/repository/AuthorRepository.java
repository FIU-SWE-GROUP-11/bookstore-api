package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<M_Author, Integer> {
    // Custom query methods
}
