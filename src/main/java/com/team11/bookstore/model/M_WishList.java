package com.team11.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"WishList\"")
public class M_WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"wishListID\"")
    private Integer wishListID;

    @ManyToOne
    @JoinColumn(name = "\"userID\"", nullable = false)
    private M_User user;

    @Column(nullable = false)
    private String name;

    public M_WishList() {}

    public M_WishList(Integer wishListID, M_User user, String name) {
        this.wishListID = wishListID;
        this.user = user;
        this.name = name;
    }


    public Integer getWishListID() {
        return wishListID;
    }

    public void setWishListID(Integer wishListID) {
        this.wishListID = wishListID;
    }

    public M_User getUser() {
        return user;
    }

    public void setUser(M_User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
