package com.team11.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Authors\"")
public class M_Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorsID")
    private Integer authorsID;
    private String authorsName;
    private String biography;
    private String publisher;

    public M_Author(){}

    public M_Author(Integer authorsID, String authorsName, String biography, String publisher) {
        this.authorsID = authorsID;
        this.authorsName = authorsName;
        this.biography = biography;
        this.publisher = publisher;
    }

    public Integer getAuthorsID() {
        return authorsID;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
