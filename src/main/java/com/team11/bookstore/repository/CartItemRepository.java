package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<M_CartItem, Integer> {
    // Custom query methods
}
