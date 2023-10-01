package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<M_CreditCard, Integer> {
    // Custom query methods
}
