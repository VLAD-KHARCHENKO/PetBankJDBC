package com.project.petbank.view;


import java.math.BigDecimal;
import java.util.Objects;

public class AccountDTO {
    private long id;
 private BigDecimal balance;
    private String number;
    private String active;

    public AccountDTO(long id, BigDecimal balance, String number, String active) {
        this.id = id;
        this.balance = balance;
        this.number = number;
        this.active = active;
    }

    public AccountDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", balance=" + balance +
                ", number='" + number + '\'' +
                ", active='" + active + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return id == that.id &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(number, that.number) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, number, active);
    }
}
