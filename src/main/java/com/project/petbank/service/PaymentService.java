package com.project.petbank.service;


import com.project.petbank.model.Account;
import com.project.petbank.model.Card;
import com.project.petbank.model.Payment;
import com.project.petbank.model.User;
import com.project.petbank.repository.impl.AccountDaoImpl;
import com.project.petbank.repository.impl.CardDaoImpl;
import com.project.petbank.repository.impl.PaymentDaoImpl;
import com.project.petbank.view.CardDTO;
import com.project.petbank.view.PaymentDTO;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class PaymentService {

    private static final Logger LOG = Logger.getLogger(PaymentService.class);
    private CardDaoImpl cardDao;
    private PaymentDaoImpl paymentDao;
    private AccountDaoImpl accountDao;

    public PaymentService(CardDaoImpl cardDao, PaymentDaoImpl paymentDao, AccountDaoImpl accountDao) {
        this.cardDao = cardDao;
        this.paymentDao = paymentDao;
        this.accountDao = accountDao;
    }

    public Payment getCard(long id) {
        return paymentDao.getById(id);
    }


    /**
     * Gets User by Login from DB
     *
     * @param
     * @param accountId
     * @return
     */
    public List<PaymentDTO> getSavedPaymentByAccountNumber(Long accountId) {
        return mapToPaymentDTO(paymentDao.findAllSaveByAccountId(accountId));
    }

    public List<PaymentDTO> getPaidPaymentByAccountNumber(Long accountId) {
        return mapToPaymentDTO(paymentDao.findAllPayedByAccountId(accountId));
    }

    private List<PaymentDTO> mapToPaymentDTO(List<Payment> all) {
        return all.stream().map(payment -> {
            Account accountDebit = accountDao.getById(payment.getDebitAccountId());
            Account accountCredit = accountDao.getById(payment.getCreditAccountSd());

            return new PaymentDTO(
                    payment.getId(),
                    payment.getDate(),
                    accountDebit,
                    accountCredit,
                    payment.getAmount(),
                    payment.getDescription(),
                    payment.getStatus());

        }).collect(Collectors.toList());
    }


    /**
     * Gets List UserDTO from DB
     *
     * @return
     */


}
