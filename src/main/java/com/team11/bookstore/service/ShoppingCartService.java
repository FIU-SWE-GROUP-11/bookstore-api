package com.team11.bookstore.service;

import com.team11.bookstore.customExceptions.CustomExceptions;
import com.team11.bookstore.model.M_CartItem;
import com.team11.bookstore.model.M_ShoppingCart;
import com.team11.bookstore.repository.ShoppingCartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shpCartRepo;

    @Autowired
    private ShoppingCartItemService cartItemService;

    public M_ShoppingCart findOrCreateShoppingCart(Integer userId) {
        return shpCartRepo.findByUserID(userId).orElseGet(() -> {
            M_ShoppingCart newShoppingCart = new M_ShoppingCart();
            newShoppingCart.setUserID(userId);
            return shpCartRepo.save(newShoppingCart);
        });
    }

    public M_CartItem addBookToCart(M_ShoppingCart shoppingCart, Integer bookId) {
        return cartItemService.createCartItem(shoppingCart, bookId);
    }

    public Set<M_CartItem> getAllCartItemsByUserId(Integer userId){
        M_ShoppingCart shpCart = shpCartRepo.findByUserID(userId).orElseThrow(()-> new EntityNotFoundException("No shopping cart available for user with id " + userId + "."));

        return shpCart.getCartItems();
    }

    private Integer getItemID(Set<M_CartItem> items, Integer bookId){
        for (M_CartItem item: items){
            if (item.getBookID() == bookId){
                return item.getCartItemsID();
            }
        }

        return -1;
    }

    public void deleteItemFromCart(Integer userId, Integer bookId){
        M_ShoppingCart shpCart = shpCartRepo.findByUserID(userId).orElseThrow(()-> new EntityNotFoundException("No shopping cart available for user with id " + userId + "."));
        Set<M_CartItem> items = shpCart.getCartItems();

        Integer itemID = getItemID(items, bookId);

        if (itemID < 0){
            throw new CustomExceptions.BookNotInCartException("The book you are trying to delete is not in the cart.");
        }

        System.out.println("Trying to delete XD, " + itemID);

        cartItemService.deleteItem(itemID);


    }
}
