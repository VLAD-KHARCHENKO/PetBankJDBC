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

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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

    public List<CardDTO> getPendingCard() {
        LOG.info("CardService|getPendingCard()");
        List<Card> allPending = cardDao.getPendingCards();
        return mapToCardDTO(allPending);
    }

    public void registrationCard(CardName cardName, User user) {
        long cardNumber = generateCardNumber();
        LOG.info("cardNumber=" + cardNumber);
        Account account = createAccount(user.getId(), cardNumber);
        LOG.info("createAccount" + account);
        Card newCard = new Card(
                cardName, cardNumber, CardCondition.ACTIVE, account.getId());
        cardDao.create(newCard);
    }

    public Long generateCardNumber() {
        Card maxNumberCard = (cardDao.findMaxValueByNumber(1L));
        long newNumber = maxNumberCard.getNumber();
        LOG.info("generateCardNumber" + newNumber);
        return ++newNumber;
    }

    public Account createAccount(long userId, long cardNumber) {
        LOG.info("createAccount method");
        String accountNumber = "UA2600" + cardNumber;
        LOG.info("accountNumber:" + accountNumber);
        Account newAccount = new Account(accountNumber, BigDecimal.ZERO, true, userId);
        return accountDao.create(newAccount);
    }

    /**
     * Gets List CardDTO from DB
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

    public List<CardDTO> getAllPaginated(int page, int size, String sort, String currentDirection) {
        LOG.info("getAllPaginated:");
        String direction = currentDirection.equals("asc") ? "ACS" : "DESC";
        List<Card> all = cardDao.getAllPaginated(page, size, sort, direction);
        LOG.info("Get all users:" + all);
        return mapToCardDTO(all);
    }

    public void refillCard(long cardId, BigDecimal amount) {
        Card changeCard = cardDao.getById(cardId);
        Account account = accountDao.getById(changeCard.getAccountId());
        account.setBalance(account.getBalance().add(amount));
        accountDao.update(account);
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

    public void changeConditionCard(long id, CardCondition cardCondition) {
        Card updatedCard = cardDao.getById(id);
        updatedCard.setCardCondition(cardCondition);
        LOG.info("change card condition");
        cardDao.update(updatedCard);
    }

    public List<CardDTO> findAllByUserIdAndCardCondition(long id) {
        List<Account> accounts = accountDao.getAllByFieldId(id);
        LOG.info("List<Account> accounts = " + accounts);
        List<Card> all = accounts.stream().map(account -> cardDao.getActiveCardByFieldId(account.getId())).filter(Objects::nonNull)
                .collect(Collectors.toList());
        LOG.info("List<Card> all = " + all);
        return mapToCardDTO(all);
    }

    /**
     * Adds List Card to List CardDTO
     *
     * @param all
     * @return
     */
    private List<CardDTO> mapToCardDTO(List<Card> all) {
        return all.stream().map(card -> {
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

}
