package com.project.petbank.view;

import com.project.petbank.model.enums.CardName;

import java.util.Objects;

public class CardDTO {
    private long id;
    private CardName cardName;
    private String number;
    private String active;

    public CardDTO(long id, CardName cardName, String number, String active) {
        this.id = id;
        this.cardName = cardName;
        this.number = number;
        this.active = active;
    }

    public CardDTO() {
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "id=" + id +
                ", cardName=" + cardName +
                ", number='" + number + '\'' +
                ", active='" + active + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return id == cardDTO.id &&
                cardName == cardDTO.cardName &&
                Objects.equals(number, cardDTO.number) &&
                Objects.equals(active, cardDTO.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardName, number, active);
    }
}
