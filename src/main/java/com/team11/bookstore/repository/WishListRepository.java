package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<M_WishList, Integer> {
    // Custom query methods here
}
