package com.project.petbank.model;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private long id;
    private String number;
    private BigDecimal balance;
    private List<Card> cards;
    private boolean isActive;
    private long userId;

    public Account() {
    }

    public Account(long id, String number, BigDecimal balance, List<Card> cards, boolean isActive, long userId) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.cards = cards;
        this.isActive = isActive;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", cards=" + cards +
                ", isActive=" + isActive +
                ", userId=" + userId +
                '}';
    }
}
