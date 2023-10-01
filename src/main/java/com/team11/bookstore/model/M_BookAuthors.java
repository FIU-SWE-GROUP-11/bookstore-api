package com.team11.bookstore.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"BookAuthors\"")
public class M_BookAuthors {

    @EmbeddedId
    private BookAuthorsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookID")
    @JoinColumn(name = "bookID")
    private M_Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("authorsID")
    @JoinColumn(name = "authorsID")
    private M_Author author;

    public M_BookAuthors() {
    }

    public M_BookAuthors(M_Book book, M_Author author) {
        this.book = book;
        this.author = author;
        this.id = new BookAuthorsId(book.getBookID(), author.getAuthorsID());
    }

    public BookAuthorsId getId() {
        return id;
    }

    public void setId(BookAuthorsId id) {
        this.id = id;
    }

    public M_Book getBook() {
        return book;
    }

    public void setBook(M_Book book) {
        this.book = book;
    }

    public M_Author getAuthor() {
        return author;
    }

    public void setAuthor(M_Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        M_BookAuthors that = (M_BookAuthors) o;
        return Objects.equals(book, that.book) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }
}
