package com.team11.bookstore.service;

import com.team11.bookstore.model.M_User;
import com.team11.bookstore.model.M_WishList;
import com.team11.bookstore.repository.UserRepository;
import com.team11.bookstore.repository.WishListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UserRepository userRepository;


    public M_WishList createWishlist(Integer userId, String wishlistName) {
        //Find the user by their ID
        M_User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        //check if the user already has three wishlists.
        if (wishListRepository.countByUser(user) >= 3) {
            throw new IllegalArgumentException("User has reached the maximum amount of users");
        }
        //checks if wishlist name was already used on a wishlist for that user.
        if (wishListRepository.existsByUserAndName(user, wishlistName)) {
            throw new IllegalArgumentException("Wishlist name already exists for this user");
        }

        //new wishlist is created
        M_WishList wishlist = new M_WishList();
        wishlist.setUser(user);
        wishlist.setName(wishlistName);
        return wishListRepository.save(wishlist);
    }
}
