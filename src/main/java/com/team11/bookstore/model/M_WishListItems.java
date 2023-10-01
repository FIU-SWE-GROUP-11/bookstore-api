package com.team11.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"WishListItems\"")
public class M_WishListItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishListItemsID")
    private Integer wishListItemsID;

    @ManyToOne
    @JoinColumn(name = "wishListID", nullable = false)
    private M_WishList wishList;

    @ManyToOne
    @JoinColumn(name = "bookID", nullable = false)
    private M_Book book;

    public M_WishListItems() {}

    public M_WishListItems(Integer wishListItemsID, M_WishList wishList, M_Book book) {
        this.wishListItemsID = wishListItemsID;
        this.wishList = wishList;
        this.book = book;
    }

    public Integer getWishListItemsID() {
        return wishListItemsID;
    }

    public void setWishListItemsID(Integer wishListItemsID) {
        this.wishListItemsID = wishListItemsID;
    }

    public M_WishList getWishList() {
        return wishList;
    }

    public void setWishList(M_WishList wishList) {
        this.wishList = wishList;
    }

    public M_Book getBook() {
        return book;
    }

    public void setBook(M_Book book) {
        this.book = book;
    }
}
