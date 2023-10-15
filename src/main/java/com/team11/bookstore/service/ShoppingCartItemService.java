package com.team11.bookstore.service;

import com.team11.bookstore.model.M_CartItem;
import com.team11.bookstore.model.M_ShoppingCart;
import com.team11.bookstore.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {

    @Autowired
    private CartItemRepository cartItemRepo;

    public M_CartItem createCartItem(M_ShoppingCart shoppingCart, int bookID){
        M_CartItem cartItem = new M_CartItem(shoppingCart, bookID);

        return cartItemRepo.save(cartItem);
    }
}
