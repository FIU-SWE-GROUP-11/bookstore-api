/*
* Note: Any column name that is case-sensitive like bookName or bookID, MUST be annotated with @Column annotation.
*/

package com.team11.bookstore.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "\"Books\"")
public class M_Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"bookID\"")
    private Integer bookID;

    @Column(name = "\"ISBN\"")
    private String ISBN;

    @Column(name = "\"bookName\"")
    private String bookName;
    private String description;
    private BigDecimal price;
    private String genre;
    private String publisher;
    @Column(name = "\"yearPublished\"")
    private Integer yearPublished;
    @Column(name = "\"copiesSold\"")
    private Integer copiesSold;

    public M_Book(){}

    public M_Book(Integer bookID, String ISBN, String bookName, String description, BigDecimal price, String genre, String publisher, Integer yearPublished, Integer copiesSold) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.description = description;
        this.price = price;
        this.genre = genre;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.copiesSold = copiesSold;
    }


    public Integer getBookID() {
        return bookID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Integer getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }
}
