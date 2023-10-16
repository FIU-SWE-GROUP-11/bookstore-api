package com.team11.bookstore.model;
import jakarta.persistence.*;

@Entity
@Table(name = "\"CartItems\"")
public class M_CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"cartItemsID\"", nullable = false)
    private Integer cartItemsID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"cartID\"", nullable = false)
    private M_ShoppingCart shoppingCart;

    @Column(name = "\"bookID\"", nullable = false)
    private Integer bookID;

    public M_CartItem() {}

    public M_CartItem(M_ShoppingCart shoppingCart, Integer bookID) {
        this.shoppingCart = shoppingCart;
        this.bookID = bookID;
    }


    public Integer getCartItemsID() {
        return cartItemsID;
    }

    public void setCartItemsID(Integer cartItemsID) {
        this.cartItemsID = cartItemsID;
    }

    public M_ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(M_ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }
}
