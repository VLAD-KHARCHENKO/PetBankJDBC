package com.project.petbank.service;


import com.project.petbank.model.Account;
import com.project.petbank.repository.impl.AccountDaoImpl;
import com.project.petbank.view.AccountDTO;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class AccountService {

    private static final Logger LOG = Logger.getLogger(AccountService.class);
    private AccountDaoImpl accountDao;

    public AccountService(AccountDaoImpl accountDao) {
        this.accountDao = accountDao;
    }


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
