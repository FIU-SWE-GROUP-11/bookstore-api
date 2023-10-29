package com.team11.bookstore.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "\"CreditCards\"")
public class M_CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "\"userID\"", nullable = false)
    private M_User user;
    @Column(name = "\"cardNumber\"")
    private Long cardNumber;
    @Column(name = "\"expirationDate\"")
    private Date expirationDate;
    @Column(name = "\"CVV\"")
    private Integer cvv;
    @Column(name = "\"cardName\"")
    private String cardName;

    public M_CreditCard(){}

    public M_CreditCard(Integer id, M_User user, Long cardNumber, Date expirationDate, Integer cvv, String cardName) {
        this.id = id;
        this.user = user;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardName = cardName;
    }
    public M_CreditCard(Integer id, Long cardNumber, Date expirationDate, Integer cvv, String cardName) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardName = cardName;
    }
    public M_User getUser() {
        return user;
    }

    public void setUser(M_User user) {
        this.user = user;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
