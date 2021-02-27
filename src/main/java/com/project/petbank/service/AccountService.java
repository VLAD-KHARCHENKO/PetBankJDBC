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


    public Account getCardByNumber(String number) {
        return accountDao.getByNumber(number);
    }




    public List<AccountDTO> getAll() {
        List<Account> all = accountDao.getAll();
        return mapToAccountDTO(all);
    }

    public List<AccountDTO> getAllPaginated(int page, int size, String sort, String currentDirection) {
        LOG.info("getAllPaginated:");
        String direction = currentDirection.equals("asc") ? "ASC" : "DESC";
        List<Account> all = accountDao.getAllPaginated(page, size, sort, direction);
        LOG.info("Get all users:" + all);
        return mapToAccountDTO(all);
    }


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
