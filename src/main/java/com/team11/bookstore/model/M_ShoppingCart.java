package com.team11.bookstore.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "\"ShoppingCart\"")
public class M_ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID", nullable = false)
    private Integer cartID;

    @Column(name = "userID", nullable = false)
    private Integer userID;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<M_CartItem> cartItems;

    public M_ShoppingCart() {
    }

    public M_ShoppingCart(Integer cartID, Integer userID, Set<M_CartItem> cartItems) {
        this.cartID = cartID;
        this.userID = userID;
        this.cartItems = cartItems;
    }

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Set<M_CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<M_CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
