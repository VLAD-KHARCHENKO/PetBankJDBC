package com.project.petbank.model;

import com.project.petbank.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private long id;
    private LocalDateTime date;
    private long debitAccountId;
    private long creditAccountSd;
    private BigDecimal amount;
    private String description;
    private Status status;

    public Payment() {
    }

    public Payment(long id, long debitAccountId, long creditAccountSd, BigDecimal amount, String description, Status status) {
        this.id = id;
        this.debitAccountId = debitAccountId;
        this.creditAccountSd = creditAccountSd;
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

    public long getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(long debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public long getCreditAccountSd() {
        return creditAccountSd;
    }

    public void setCreditAccountSd(long creditAccountSd) {
        this.creditAccountSd = creditAccountSd;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                debitAccountId == payment.debitAccountId &&
                creditAccountSd == payment.creditAccountSd &&
                Objects.equals(amount, payment.amount) &&
                Objects.equals(description, payment.description) &&
                status == payment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountSd, amount, description, status);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", debitAccountId=" + debitAccountId +
                ", creditAccountSd=" + creditAccountSd +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
