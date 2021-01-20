package com.project.petbank.model;


import com.project.petbank.model.enums.CardName;

public class Card {
    private long id;
    private CardName cardName;
    private boolean isActive;
    private long accountId;

    public Card() {
    }

    public Card(long id, CardName cardName, boolean isActive, long accountId) {
        this.id = id;
        this.cardName = cardName;
        this.isActive = isActive;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CardName getCardName() {
        return cardName;
    }

    public void setCardName(CardName cardName) {
        this.cardName = cardName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardName=" + cardName +
                ", isActive=" + isActive +
                ", accountId=" + accountId +
                '}';
    }
}
