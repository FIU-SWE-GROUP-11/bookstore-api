package com.team11.bookstore.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "\"Ratings\"")
public class M_Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingsID")
    private Integer ratingsID;

    @ManyToOne
    @JoinColumn(name = "\"userID\"", nullable = false)
    private M_User user;


    @ManyToOne
    @JoinColumn(name = "\"bookID\"", nullable = false)
    private M_Book book;

    @Column(name = "\"ratingValue\"", nullable = false)
    private Integer ratingValue;

    private Timestamp created_at;

    public M_Ratings() {}

    public M_Ratings(Integer ratingsID, M_User user, M_Book book, Integer ratingValue, Timestamp created_at) {
        this.ratingsID = ratingsID;
        this.user = user;
        this.book = book;
        this.ratingValue = ratingValue;
        this.created_at = created_at;
    }

    public Integer getRatingsID() {
        return ratingsID;
    }

    public void setRatingsID(Integer ratingsID) {
        this.ratingsID = ratingsID;
    }

    public M_User getUser() {
        return user;
    }

    public void setUser(M_User user) {
        this.user = user;
    }

    public M_Book getBook() {
        return book;
    }

    public void setBook(M_Book book) {
        this.book = book;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
