package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingsRepository extends JpaRepository<M_Ratings, Integer> {
    // Custom query methods
}
