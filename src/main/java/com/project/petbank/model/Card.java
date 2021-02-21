package com.project.petbank.model;


import com.project.petbank.model.enums.CardCondition;
import com.project.petbank.model.enums.CardName;
import lombok.Builder;

@Builder
public class Card {

    private long id;
    private CardName cardName;
    private String number;
    private CardCondition cardCondition;
    private long accountId;

    public Card() {
    }

    public Card(long id, CardName cardName, String number, CardCondition cardCondition, long accountId) {
        this.id = id;
        this.cardName = cardName;
        this.number = number;
        this.cardCondition = cardCondition;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CardCondition getCardCondition() {
        return cardCondition;
    }

    public void setCardCondition(CardCondition cardCondition) {
        this.cardCondition = cardCondition;
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
                ", number='" + number + '\'' +
                ", cardCondition=" + cardCondition +
                ", accountId=" + accountId +
                '}';
    }
}
