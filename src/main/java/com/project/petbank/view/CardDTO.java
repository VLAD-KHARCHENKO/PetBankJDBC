package com.project.petbank.view;

import com.project.petbank.model.Account;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.CardCondition;
import com.project.petbank.model.enums.CardName;

import java.util.Objects;

public class CardDTO {
    private long id;
    private CardName cardName;
    private String number;
    private CardCondition cardCondition;
    private User user;
    private Account account;

    public CardDTO(long id, CardName cardName, String number, CardCondition cardCondition, User user, Account account) {
        this.id = id;
        this.cardName = cardName;
        this.number = number;
        this.cardCondition = cardCondition;
        this.user = user;
        this.account = account;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "id=" + id +
                ", cardName=" + cardName +
                ", number='" + number + '\'' +
                ", cardCondition=" + cardCondition +
                ", user=" + user +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDTO)) return false;
        CardDTO cardDTO = (CardDTO) o;
        return getId() == cardDTO.getId() &&
                getCardName() == cardDTO.getCardName() &&
                Objects.equals(getNumber(), cardDTO.getNumber()) &&
                getCardCondition() == cardDTO.getCardCondition()
                && Objects.equals(getUser(), cardDTO.getUser()) &&
                Objects.equals(getAccount(), cardDTO.getAccount())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCardName(), getNumber(), getCardCondition()
                , getUser(), getAccount()
        );
    }
}
