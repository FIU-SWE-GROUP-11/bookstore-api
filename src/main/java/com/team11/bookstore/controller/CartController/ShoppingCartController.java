package com.team11.bookstore.controller.CartController;

import com.team11.bookstore.model.M_CartItem;
import com.team11.bookstore.model.M_ShoppingCart;
import com.team11.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;


    @GetMapping
    public ResponseEntity<?> getAllCartItems(@RequestParam int uid){
        Set<M_CartItem> cartItems;

        // TODO once book service expands, use it to get the book names and add the book names to result list instead of ID
        List<Integer> result = new ArrayList<Integer>();

        try {
           cartItems  = cartService.getAllCartItemsByUserId(uid);

           for (M_CartItem item : cartItems ){
               result.add(item.getBookID());
           }

        }catch(Exception e){
            // TODO perform more validations and return more specific errors >> e.g. is the user id valid? Is the cart empty? Does the user even have a cart?

            return ResponseEntity.badRequest().body("Something went wrong... We couldn't find any cart items in your shopping cart.");
        }

        return ResponseEntity.ok(result.toString());
    }

    public static class RequestCartItem{
        private int userID;
        private int bookID;

        public RequestCartItem(int uid, int bookId){
            this.userID = uid;
            this.bookID = bookId;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getBookID() {
            return bookID;
        }

        public void setBookID(int bookID) {
            this.bookID = bookID;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBookToCart(@RequestBody RequestCartItem cartItem){
        M_ShoppingCart userShoppingCart;
        M_CartItem newItem;

        System.out.println(cartItem.userID);

        try{
            userShoppingCart = cartService.findOrCreateShoppingCart(cartItem.getUserID());
        }catch (Exception e){
            // if we cannot create or find cart by userid then there is no such user.

            return ResponseEntity.badRequest().body("User does not exist.");
        }

        System.out.println(userShoppingCart.getCartID());
        System.out.println("Book id: " + cartItem.getBookID());

        try{
            newItem = cartService.addBookToCart(userShoppingCart, cartItem.getBookID());

            if((newItem.getCartItemsID() == null)){
                throw new CustomExceptions.BookAlreadyInTheCartException("Item already in the cart");
            }

        }catch(Exception e){
            if(e instanceof CustomExceptions.BookAlreadyInTheCartException){
                return ResponseEntity.status(409).body(e.getMessage()); // 409 means that it already exists
            }

            // TODO Once Book Service becomes robust, change this with custom InvalidBookIDException | can be thrown before newItem is added to cart
            if(e instanceof DataIntegrityViolationException){
                return ResponseEntity.badRequest().body("Book does not exist.");
            }

            System.out.println(e.toString());

            return ResponseEntity.badRequest().body("Something went wrong");
        }

        return ResponseEntity.ok(newItem.getBookID());
    }
}


