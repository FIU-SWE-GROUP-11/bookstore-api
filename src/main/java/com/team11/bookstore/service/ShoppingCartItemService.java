package com.team11.bookstore.service;

import com.team11.bookstore.customExceptions.CustomExceptions;
import com.team11.bookstore.model.M_CartItem;
import com.team11.bookstore.model.M_ShoppingCart;
import com.team11.bookstore.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {

    @Autowired
    private CartItemRepository cartItemRepo;

    public M_CartItem createCartItem(M_ShoppingCart shoppingCart, int bookID){
        if (!cartItemRepo.existsByShoppingCart_CartIDAndBookID(shoppingCart.getCartID(), bookID)) {
            System.out.println("Shopping Cart ID from repo: " + shoppingCart.getCartID());
            M_CartItem cartItem = new M_CartItem(shoppingCart, bookID);

            shoppingCart.getCartItems().add(cartItem);

            return cartItemRepo.save(cartItem);
        }

        return new M_CartItem();
    }

    @Transactional
    public void deleteItem(Integer cartItemId){
        M_CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new CustomExceptions.BookNotInCartException("CartItem not found: " + cartItemId));
        M_ShoppingCart shoppingCart = cartItem.getShoppingCart();
        shoppingCart.getCartItems().remove(cartItem);
        cartItemRepo.delete(cartItem);
    }

}
