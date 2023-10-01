package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<M_User, Integer> {
    // Custom query methods
}
