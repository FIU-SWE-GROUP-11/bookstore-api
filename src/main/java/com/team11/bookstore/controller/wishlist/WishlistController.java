package com.team11.bookstore.controller.wishlist;

import com.team11.bookstore.model.M_WishList;
import com.team11.bookstore.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishListService;

    @PostMapping("/create")
    public M_WishList createWishlist(@RequestParam Integer userId, @RequestParam String wishlistName) {
        return wishListService.createWishlist(userId, wishlistName);
    }

}
