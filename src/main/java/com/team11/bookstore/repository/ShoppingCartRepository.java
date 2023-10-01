package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<M_ShoppingCart, Integer> {
    // Custom query methods
}
