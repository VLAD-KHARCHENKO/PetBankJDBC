package com.project.petbank.service;


import com.project.petbank.model.Account;
import com.project.petbank.model.Card;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.CardCondition;
import com.project.petbank.model.enums.CardName;
import com.project.petbank.repository.impl.AccountDaoImpl;
import com.project.petbank.repository.impl.CardDaoImpl;
import com.project.petbank.repository.impl.UserDaoImpl;
import com.project.petbank.view.CardDTO;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class CardService {

    private static final Logger LOG = Logger.getLogger(CardService.class);
    private CardDaoImpl cardDao;
    private AccountDaoImpl accountDao;
    private UserDaoImpl userDao;

    public CardService(CardDaoImpl cardDao, AccountDaoImpl accountDao, UserDaoImpl userDao) {
        this.cardDao = cardDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
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


    public List<CardDTO> getPendingCard() {
        LOG.info("CardService|getPendingCard()");
        List<Card> allPending = cardDao.getPendingCards();
        return mapToCardDTO(allPending);
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
                .cardCondition(CardCondition.ACTIVE)
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

    public List<CardDTO> getAllByUserId(Long userId) {
        List<Account> userAccounts = accountDao.getAllByFieldId(userId);
        List<Card> userCards = userAccounts.stream()
                .map(account -> cardDao.getByFieldId(account.getId()))
                .collect(Collectors.toList());
        return mapToCardDTO(userCards);
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
        return all.stream().map(card -> {
//            Card cardProfile = cardDao.getById(card.getId());
            Account account = accountDao.getById(card.getAccountId());
            User user = userDao.getById(account.getUserId());
            return new CardDTO(
                    card.getId(),
                    card.getCardName(),
                    card.getNumber(),
                    card.getCardCondition(),
                    user,
                    account);


        }).collect(Collectors.toList());
    }


    public CardDTO getCardByAccountId(Long accountId) {
       Card card = cardDao.getByFieldId(accountId);
        Account account = accountDao.getById(accountId);
        User user = userDao.getById(account.getUserId());
        return new CardDTO(
                card.getId(),
                card.getCardName(),
                card.getNumber(),
                card.getCardCondition(),
                user,
                account);
    }
}
