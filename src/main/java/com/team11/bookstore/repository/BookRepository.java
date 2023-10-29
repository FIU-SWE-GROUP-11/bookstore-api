package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<M_Book, Integer> {
    // Custom query methods
    List<M_Book> findByGenre(String genre);
    List<M_Book> findByCopiesSold(Integer copiesSold);
    List<M_Book> findByPublisher(String publisher);

    @Query("SELECT b FROM M_Book b WHERE b.bookID IN (SELECT r.book.bookID FROM M_Ratings r WHERE r.ratingValue >= :rating)")
    List<M_Book> findBooksByRating(@Param("rating") Integer rating);


}
