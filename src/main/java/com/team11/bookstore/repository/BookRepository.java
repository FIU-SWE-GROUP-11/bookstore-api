package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<M_Book, Integer> {
    // Custom query methods
    List<M_Book> findByGenre(String genre);
    List<M_Book> findByCopiesSold(Integer copiesSold);
}
