package com.team11.bookstore.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "\"Comments\"")
public class M_Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"commentID\"")
    private Integer commentID;

    @ManyToOne
    @JoinColumn(name = "\"userID\"", nullable = false)
    private M_User user;

    @ManyToOne
    @JoinColumn(name = "\"bookID\"", nullable = false)
    private M_Book book;

    @Column(name = "\"commentDescription\"", nullable = false)
    private String commentDescription;

    private Timestamp created_at;

    public M_Comments() {}

    public M_Comments(Integer commentID, M_User user, M_Book book, String commentDescription, Timestamp created_at) {
        this.commentID = commentID;
        this.user = user;
        this.book = book;
        this.commentDescription = commentDescription;
        this.created_at = created_at;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
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

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
