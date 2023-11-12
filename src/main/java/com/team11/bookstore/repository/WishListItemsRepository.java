package com.team11.bookstore.repository;

import com.team11.bookstore.model.M_Book;
import com.team11.bookstore.model.M_WishList;
import com.team11.bookstore.model.M_WishListItems;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishListItemsRepository extends JpaRepository<M_WishListItems, Integer> {
    // Custom query methods

    //checks that the book exists in the wishlist
    boolean existsByWishListAndBook(M_WishList wishlist, M_Book book);

    //Finds the wishlist item using wishlist and book.
    M_WishListItems findByWishListAndBook(M_WishList wishlist, M_Book book);

    List<M_WishListItems> findByWishList_WishListID(Integer wishlistId);
}
