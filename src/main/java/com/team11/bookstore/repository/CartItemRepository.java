package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<M_CartItem, Integer> {
    // Custom query methods

    boolean existsByBookID(Integer bookID);

    // checks if there is already a cart item with the given CartID and BookID
    boolean existsByShoppingCart_CartIDAndBookID(Integer cartID, Integer bookID);
}
