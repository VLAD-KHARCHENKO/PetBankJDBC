package com.project.petbank.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Account {
    private long id;
    private String number;
    private BigDecimal balance;
    private boolean isActive;
    private long userId;

    public Account(long id, String number, BigDecimal balance, boolean isActive, long userId) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.isActive = isActive;
        this.userId = userId;
    }

    public Account(String number, BigDecimal balance, boolean isActive, long userId) {
        this.number = number;
        this.balance = balance;
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
                ", isActive=" + isActive +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getId() == account.getId() &&
                isActive() == account.isActive() &&
                getUserId() == account.getUserId() &&
                Objects.equals(getNumber(), account.getNumber()) &&
                Objects.equals(getBalance(), account.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getBalance(), isActive(), getUserId());
    }
}
