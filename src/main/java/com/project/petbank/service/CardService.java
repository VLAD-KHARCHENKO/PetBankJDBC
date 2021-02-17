package com.project.petbank.service;


import com.project.petbank.model.Card;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.CardName;
import com.project.petbank.model.enums.Role;
import com.project.petbank.repository.impl.CardDaoImpl;
import com.project.petbank.utils.PasswordsUtil;
import com.project.petbank.view.CardDTO;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class CardService {

    private static final Logger LOG = Logger.getLogger(CardService.class);
    private CardDaoImpl cardDao;

    public CardService(CardDaoImpl cardDao) {
        this.cardDao = cardDao;
    }

    /**
     * Validates User's Login and checks if it corresponds with password
     *
     * @param login
     * @param password
     * @return
     */
//    public boolean validateCard(long id) {
//        Card card = cardDao.getById(id);
//        LOG.info("Get user by login:" + card);
//        if (card != null && PasswordsUtil.verifyHash(password, card.getPassword())) {
//            return true;
//        }
//        return false;
//    }

    /**
     * Validates if Login exists in DB
     *
     * @param login
     * @return
     */
//    public boolean validateLogin(String login) {
//        return !userDao.isUserExists(login);
//    }

    /**
     * Validates whether confirmPasswords corresponds with Password
     *
     * @param password
     * @param confirmPassword
     * @return
     */
//    public boolean validatePassword(String password, String confirmPassword) {
//        if (password.equals(confirmPassword)) return true;
//        return false;
//    }

    /**
     * Gets User by ID from DB
     *
     * @param id
     * @return
     */
    public Card getCard(long id) {
        return cardDao.getById(id);
    }


    public List<Card> getPendingCard() {
        return cardDao.getPendingCards();
    }

    /**
     * Gets User by Login from DB
     *
     * @param
     * @return
     */
    public Card getCardByNumber(String number) {
        return cardDao.getByNumber(number);
    }


    public Card registrationCard(String cardName, String number, long accountId) {
        Card newCard = Card.builder()
                .cardName(CardName.UNIVERSAL)
                .number(number)
                .isActive(true)
                .accountId(accountId)
                .build();
        cardDao.create(newCard);
        return newCard;
    }

    /**
     * Gets List UserDTO from DB
     *
     * @return
     */
    public List<CardDTO> getAll() {
        List<Card> all = cardDao.getAll();
        return mapToCardDTO(all);
    }

    public List<CardDTO> getAllPaginated(int page, int size) {
        LOG.info("getAllPaginated:");
        List<Card> all = cardDao.getAllPaginated(page, size);
        LOG.info("Get all users:" + all);
        return mapToCardDTO(all);
    }

    /**
     * Adds List User to List UserDTO
     *
     * @param all
     * @return
     */
    private List<CardDTO> mapToCardDTO(List<Card> all) {
        return all.stream().map(cards -> {
            Card cardProfile = cardDao.getById(cards.getId());
            CardDTO cardDTO = new CardDTO(
                    cardProfile.getId(),
                    cardProfile.getCardName(),
                    cardProfile.getNumber(),
                    cardProfile.isActive() ? "Active" : "Blocked");

            return cardDTO;
        }).collect(Collectors.toList());
    }
}
