package com.team11.bookstore.controller.CartController;

import com.team11.bookstore.customExceptions.CustomExceptions;
import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_CartItem;
import com.team11.bookstore.model.M_ShoppingCart;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.repository.BookRepository;
import com.team11.bookstore.repository.UserRepository;
import com.team11.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo; // TODO pending bookService update >> replace with BookService when update is complete

    static class ShpCartBookObjectNotation {
        private Integer id;
        private String title;
        private BigDecimal price;

        public ShpCartBookObjectNotation(Integer id, String title, BigDecimal price) {
            this.id = id;
            this.title = title;
            this.price = price;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }


    }

    @GetMapping
    public ResponseEntity<?> getAllCartItems(@RequestParam int uid){
        Set<M_CartItem> cartItems;

        List<ShpCartBookObjectNotation> result = new ArrayList<ShpCartBookObjectNotation>();


        try {
            Optional<M_User> user = userRepo.findById(uid);

            if(user.isEmpty()){
                throw new CustomExceptions.UserDoesNotExistException("Invalid user provided");
            }


           cartItems  = cartService.getAllCartItemsByUserId(uid);

            if (cartItems == null || cartItems.isEmpty()) {
                throw new CustomExceptions.EmptyCartException("The cart for the provided user id is empty.");
            }

            System.out.println(cartItems.size());

           for (M_CartItem item : cartItems ){
               Optional<M_Book> book = bookRepo.findById(item.getBookID());  // TODO pending bookService update

               if(book.isPresent()){

                   M_Book actualBook = book.get();

                   Integer id = actualBook.getBookID();
                   String title = actualBook.getBookName();
                   BigDecimal price = actualBook.getPrice();

                   ShpCartBookObjectNotation newBook = new ShpCartBookObjectNotation(id, title, price);

                   result.add(newBook);
               }else{
                   throw new CustomExceptions.UnableToProcessOneOrMoreBooksException("Something went wrong while retrieving the book details. Data may be corrupted");
               }

           }

        }catch(CustomExceptions.UnableToProcessOneOrMoreBooksException e){ // TODO perform more validations and return more specific errors >> e.g. is the user id valid? Is the cart empty? Does the user even have a cart?
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(CustomExceptions.UserDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){

            return ResponseEntity.badRequest().body("Something went wrong... We couldn't find any cart items in your shopping cart.");
        }

        return ResponseEntity.ok(result);
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

    @GetMapping("/subtotal")
    public ResponseEntity<?> getCartSubtotal(@RequestParam int uid){
        Set<M_CartItem> cartItems;

        BigDecimal subtotal = new BigDecimal(0);


        try {
            Optional<M_User> user = userRepo.findById(uid);

            if(user.isEmpty()){
                throw new CustomExceptions.UserDoesNotExistException("Invalid user provided");
            }


            cartItems  = cartService.getAllCartItemsByUserId(uid);

            if (cartItems == null || cartItems.isEmpty()) {
                throw new CustomExceptions.EmptyCartException("The cart for the provided user id is empty.");
            }


            for (M_CartItem item : cartItems ){
                Optional<M_Book> book = bookRepo.findById(item.getBookID());  // TODO pending bookService update

                if(book.isPresent()){

                    M_Book actualBook = book.get();

                    BigDecimal price = actualBook.getPrice();

                    subtotal = subtotal.add(price);
                }else{
                    throw new CustomExceptions.UnableToProcessOneOrMoreBooksException("Something went wrong while retrieving the book details. Data may be corrupted");
                }

            }

        }catch(CustomExceptions.UnableToProcessOneOrMoreBooksException e){ // TODO perform more validations and return more specific errors >> e.g. is the user id valid? Is the cart empty? Does the user even have a cart?
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(CustomExceptions.UserDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(CustomExceptions.EmptyCartException e){
            return ResponseEntity.ok(0.00);
        }
        catch(Exception e){

            return ResponseEntity.badRequest().body("Something went wrong... We couldn't find any cart items in your shopping cart.");
        }

        return ResponseEntity.ok("{\"subtotal\" : "+subtotal + "}");
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


