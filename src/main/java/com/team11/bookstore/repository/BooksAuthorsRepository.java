package com.team11.bookstore.repository;

import com.team11.bookstore.model.BookAuthorsId;
import com.team11.bookstore.model.M_BookAuthors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksAuthorsRepository extends JpaRepository<M_BookAuthors, BookAuthorsId> {
    // Custom query methods
}

