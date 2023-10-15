package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_WishListItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListItemsRepository extends JpaRepository<M_WishListItems, Integer> {
    // Custom query methods


}
