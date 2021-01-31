package com.project.petbank.service;


import com.project.petbank.model.Account;
import com.project.petbank.model.Card;
import com.project.petbank.model.enums.CardName;
import com.project.petbank.repository.impl.AccountDaoImpl;
import com.project.petbank.repository.impl.CardDaoImpl;
import com.project.petbank.view.AccountDTO;
import com.project.petbank.view.CardDTO;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class AccountService {

    private static final Logger LOG = Logger.getLogger(AccountService.class);
    private AccountDaoImpl accountDao;

    public AccountService(AccountDaoImpl accountDao) {
        this.accountDao = accountDao;
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
    public Account getCard(long id) {
        return accountDao.getById(id);
    }

    /**
     * Gets User by Login from DB
     *
     * @param
     * @return
     */
    public Account getCardByNumber(String number) {
        return accountDao.getByNumber(number);
    }


//    public Account registrationAccount(String cardName, String number, long accountId) {
//        Account newAccount = Account.builder()
//                .cardName(CardName.UNIVERSAL)
//                .number(number)
//                .isActive(true)
//                .accountId(accountId)
//                .build();
//        accountDao.create(newCard);
//        return newCard;
//    }

    /**
     * Gets List UserDTO from DB
     *
     * @return
     */
    public List<AccountDTO> getAll() {
        List<Account> all = accountDao.getAll();
        return mapToAccountDTO(all);
    }

    public List<AccountDTO> getAllPaginated(int page, int size) {
        LOG.info("getAllPaginated:");
        List<Account> all = accountDao.getAllPaginated(page, size);
        LOG.info("Get all users:" + all);
        return mapToAccountDTO(all);
    }

    /**
     * Adds List User to List UserDTO
     *
     * @param all
     * @return
     */
    private List<AccountDTO> mapToAccountDTO(List<Account> all) {
        return all.stream().map(account -> {
            Account accountProfile = accountDao.getById(account.getId());
            AccountDTO accountDTO = new AccountDTO(
                    accountProfile.getId(),
                    accountProfile.getBalance(),
                    accountProfile.getNumber(),
                    accountProfile.isActive() ? "Active" : "Blocked");

            return accountDTO;
        }).collect(Collectors.toList());
    }
}
