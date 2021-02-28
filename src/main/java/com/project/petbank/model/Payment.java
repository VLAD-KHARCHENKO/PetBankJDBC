package com.project.petbank.model;

import com.project.petbank.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private long id;
    private LocalDateTime date;
    private long debitAccountId;
    private long creditAccountId;
    private BigDecimal amount;
    private String description;
    private Status status;

    public Payment(long id, LocalDateTime date, long debitAccountId, long creditAccountSd, BigDecimal amount, String description, Status status) {
        this.id = id;
        this.date = date;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountSd;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Payment(LocalDateTime date, long debitAccountId, long creditAccountId, BigDecimal amount, String description, Status status) {
        this.date = date;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(long debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public long getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(long creditAccountId) {
        this.creditAccountId = creditAccountId;
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
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", debitAccountId=" + debitAccountId +
                ", creditAccountSd=" + creditAccountId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId() == payment.getId() &&
                getDebitAccountId() == payment.getDebitAccountId() &&
                getCreditAccountId() == payment.getCreditAccountId() &&
                Objects.equals(getDate(), payment.getDate()) &&
                Objects.equals(getAmount(), payment.getAmount()) &&
                Objects.equals(getDescription(), payment.getDescription()) &&
                getStatus() == payment.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getDebitAccountId(), getCreditAccountId(), getAmount(), getDescription(), getStatus());
    }

}
