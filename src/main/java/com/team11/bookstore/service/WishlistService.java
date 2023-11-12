package com.team11.bookstore.service;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_User;
import com.team11.bookstore.model.M_WishList;
import com.team11.bookstore.model.M_WishListItems;
import com.team11.bookstore.repository.BookRepository;
import com.team11.bookstore.repository.UserRepository;
import com.team11.bookstore.repository.WishListItemsRepository;
import com.team11.bookstore.repository.WishListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishListItemsRepository wishListItemsRepository;

    @Autowired
    private BookRepository bookRepository;

    public M_WishList createWishlist(Integer userId, String wishlistName) {
        //Find the user by their ID
        M_User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        //check if the user already has three wishlists.
        if (wishListRepository.countByUser(user) >= 3) {
            throw new IllegalArgumentException("User has reached the maximum amount of wishlists, which is 3");
        }else if (wishListRepository.existsByUserAndName(user, wishlistName)) {
            throw new IllegalArgumentException("Wishlist name already exists for this user");
        }

        //new wishlist is created
        M_WishList wishlist = new M_WishList();
        wishlist.setUser(user);
        wishlist.setName(wishlistName);
        return wishListRepository.save(wishlist);
    }

    public void addBookToWishlist(Integer bookId, Integer wishlistId) {
        //Retrieve the wishlist and book by its ID, or throw an exception when not found.
        M_WishList wishlist = wishListRepository.findById(wishlistId).orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));
        M_Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        //checks that the book is already not in the wishlist
        if(wishListItemsRepository.existsByWishListAndBook(wishlist, book)){
            throw new IllegalArgumentException("Book already exists in the wishlist");
        }

        M_WishListItems wishListItem = new M_WishListItems();
        wishListItem.setWishList(wishlist);
        wishListItem.setBook(book);
        wishListItemsRepository.save(wishListItem);
    }

    public void removeBookFromWishlist(Integer bookId, Integer wishlistId){
        M_WishList wishlist = wishListRepository.findById(wishlistId).orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));
        M_Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        //To remove the book from the wishlist
        M_WishListItems wishListItem = wishListItemsRepository.findByWishListAndBook(wishlist, book);
        if(wishListItem == null){
            throw new IllegalArgumentException("Book does not exist in the wishlist");
        }

        wishListItemsRepository.delete(wishListItem);
    }

    public List<M_Book> getAllBooksInWishList(Integer wishlistId){
        List<M_WishListItems> wishlistItems = wishListItemsRepository.findByWishList_WishListID(wishlistId);

        if (wishlistItems == null || wishlistItems.isEmpty()){
            throw new EntityNotFoundException("Wishlist not found");
        }

        return wishlistItems.stream().map(M_WishListItems::getBook).toList();
    }

}
