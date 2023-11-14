package com.team11.bookstore.controller.CartController;

import com.team11.bookstore.customExceptions.CustomExceptions;
import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_CartItem;
import com.team11.bookstore.model.M_ShoppingCart;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.repository.BookRepository;
import com.team11.bookstore.repository.UserRepository;
import com.team11.bookstore.service.BookService;
import com.team11.bookstore.service.ShoppingCartService;
import com.team11.bookstore.service.UserService;
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
    private UserService userService;

    @Autowired
    private BookService bookService;

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
            checkUserExists(uid);


            cartItems  = cartService.getAllCartItemsByUserId(uid);

            if (cartItems == null || cartItems.isEmpty()) {
                throw new CustomExceptions.EmptyCartException("The cart for the provided user id is empty.");
            }

            System.out.println(cartItems.size());

           for (M_CartItem item : cartItems ){
               Integer bookId = item.getBookID();

               boolean hasBook = bookService.bookExists(bookId);

               if(hasBook){
                   M_Book book = bookService.getBookDetails(bookId);

                   Integer id = book.getBookID();
                   String title = book.getBookName();
                   BigDecimal price = book.getPrice();

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

    private void checkUserExists(int uid) throws CustomExceptions.UserDoesNotExistException {
        boolean userExists = userService.userExists(uid);

        if(!userExists){
            throw new CustomExceptions.UserDoesNotExistException("Invalid user provided");
        }
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
            checkUserExists(uid);


            cartItems  = cartService.getAllCartItemsByUserId(uid);

            if (cartItems == null || cartItems.isEmpty()) {
                throw new CustomExceptions.EmptyCartException("The cart for the provided user id is empty.");
            }


            for (M_CartItem item : cartItems ){
                Integer bookId = item.getBookID();

                boolean hasBook = bookService.bookExists(bookId);

                if(hasBook){
                    M_Book book = bookService.getBookDetails(bookId);

                    BigDecimal price = book.getPrice();

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

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCartItem(@RequestParam Integer uid, Integer bid){
        if(uid == null && bid == null){
            return ResponseEntity.badRequest().body("Please provide a user id and book id.");
        }

        if (uid==null){
            return ResponseEntity.badRequest().body("Please provide a user id.");
        }

        if (bid==null){
            return ResponseEntity.badRequest().body("Please provide a book id.");
        }

        try{
            boolean userExists = userService.userExists(uid);
            boolean bookExists = bookService.bookExists(bid);


            if(!userExists){
                throw new CustomExceptions.UserDoesNotExistException("User does not exist. user id  = " + uid);
            }

            if(!bookExists){
                throw new CustomExceptions.InvalidBookIDException("Book does not exist. book id = " + bid);
            }

            cartService.deleteItemFromCart(uid, bid);

            return  ResponseEntity.ok("item removed from the cart!");

        }catch (CustomExceptions.UserDoesNotExistException | CustomExceptions.InvalidBookIDException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong.");
        }

    }

    @ExceptionHandler(CustomExceptions.BookNotInCartException.class)
    public ResponseEntity<?> handleBookNotInCartException(CustomExceptions.BookNotInCartException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}


