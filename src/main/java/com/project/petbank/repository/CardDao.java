package com.project.petbank.repository;

import com.project.petbank.model.Card;

import java.util.List;

public interface CardDao {

    List<Card> getPendingCards();

    Card getByFieldId(long id);

    Card getByNumber(long number);

    Card getActiveCardByFieldId(long id);

    Card getByNumber(String number);

    Card findMaxValueByNumber(long id);

}
