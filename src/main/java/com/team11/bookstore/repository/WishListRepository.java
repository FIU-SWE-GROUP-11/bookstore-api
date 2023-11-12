package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_User;
import com.team11.bookstore.model.M_WishList;
import com.team11.bookstore.model.M_WishListItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<M_WishList, Integer> {
    // Custom query methods here
    boolean existsByUserAndName(M_User user, String name);

    @Query("SELECT COUNT(w) FROM M_WishList w WHERE w.user=:user")
    int countByUser(M_User user);


    //to find all wishlists created by a specific user
    List<M_WishList> findByUser(M_User user);

}
