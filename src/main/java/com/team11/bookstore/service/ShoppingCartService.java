package com.team11.bookstore.service;

import com.team11.bookstore.model.M_CartItem;
import com.team11.bookstore.model.M_ShoppingCart;
import com.team11.bookstore.repository.ShoppingCartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shpCartRepo;

    @Autowired
    private ShoppingCartItemService cartItemService;

    public M_ShoppingCart findOrCreateShoppingCart(int userId) {
        return shpCartRepo.findByUserID(userId).orElseGet(() -> {
            M_ShoppingCart newShoppingCart = new M_ShoppingCart();
            newShoppingCart.setUserID(userId);
            return shpCartRepo.save(newShoppingCart);
        });
    }

    public M_CartItem addBookToCart(M_ShoppingCart shoppingCart, int bookId) {
        return cartItemService.createCartItem(shoppingCart, bookId);
    }

    public Set<M_CartItem> getAllCartItemsByUserId(int userId){
        M_ShoppingCart shpCart = shpCartRepo.findByUserID(userId).orElseThrow(()-> new EntityNotFoundException("No shopping cart available for user with id " + userId + "."));

        return shpCart.getCartItems();
    }


}
