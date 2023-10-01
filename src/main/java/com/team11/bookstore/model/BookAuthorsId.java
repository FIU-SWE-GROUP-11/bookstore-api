package com.team11.bookstore.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorsId implements Serializable {

    private Integer bookID;

    private Integer authorsID;

    public BookAuthorsId() {
    }

    public BookAuthorsId(Integer bookID, Integer authorsID) {
        this.bookID = bookID;
        this.authorsID = authorsID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public void setAuthorsID(Integer authorsID) {
        this.authorsID = authorsID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public Integer getAuthorsID() {
        return authorsID;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthorsId that = (BookAuthorsId) o;
        return bookID.equals(that.bookID) &&
                authorsID.equals(that.authorsID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, authorsID);
    }
}
