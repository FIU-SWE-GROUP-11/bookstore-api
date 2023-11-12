package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingsRepository extends JpaRepository<M_Ratings, Integer> {
    @Query("SELECT AVG(r.ratingValue) FROM M_Ratings r WHERE r.book.bookID = :bookId")
    Double calculateAverageRating(@Param("bookId") Integer bookId);
}