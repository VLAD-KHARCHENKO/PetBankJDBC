package com.project.petbank.view;

import com.project.petbank.model.Account;
import com.project.petbank.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PaymentDTO {
    private long id;
    private String date;
    private Account debit;
    private Account credit;
    private BigDecimal amount;
    private String description;
    private Status status;

    public PaymentDTO(long id, LocalDateTime date, Account debit, Account credit, BigDecimal amount, String description, Status status) {
        this.id = id;
        this.date = date.format(DateTimeFormatter.ofPattern("dd.MM HH:mm"));
        this.debit = debit;
        this.credit = credit;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Account getDebit() {
        return debit;
    }

    public void setDebit(Account debit) {
        this.debit = debit;
    }

    public Account getCredit() {
        return credit;
    }

    public void setCredit(Account credit) {
        this.credit = credit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", date=" + date +
                ", debit=" + debit +
                ", credit=" + credit +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDTO)) return false;
        PaymentDTO that = (PaymentDTO) o;
        return getId() == that.getId() &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getDebit(), that.getDebit()) &&
                Objects.equals(getCredit(), that.getCredit()) &&
                Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getDebit(), getCredit(), getAmount(), getDescription(), getStatus());
    }

}
