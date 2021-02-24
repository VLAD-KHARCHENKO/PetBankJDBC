package com.project.petbank.service;


import com.project.petbank.model.Account;
import com.project.petbank.model.Card;
import com.project.petbank.model.Payment;
import com.project.petbank.model.enums.CardCondition;
import com.project.petbank.model.enums.Status;
import com.project.petbank.repository.impl.AccountDaoImpl;
import com.project.petbank.repository.impl.CardDaoImpl;
import com.project.petbank.repository.impl.PaymentDaoImpl;
import com.project.petbank.view.PaymentDTO;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public Payment getPayment(long id) {
        return paymentDao.getById(id);
    }


    public List<PaymentDTO> getSavedPaymentByAccountNumber(Long accountId) {
        return mapToPaymentDTO(paymentDao.findAllSaveByAccountId(accountId));
    }

    public List<PaymentDTO> getPaidPaymentByAccountNumber(Long accountId) {
        return mapToPaymentDTO(paymentDao.findAllPayedByAccountId(accountId));
    }


    public Payment submitPayment(long paymentId) {
        Payment payment = paymentDao.getById(paymentId);

        if (!(validateBalance(payment.getCreditAccountId(), payment.getAmount())
                && validateCard(payment.getCreditAccountId()) &&
                validateCard(payment.getDebitAccountId()))) {
            return null;
        }

        payment.setDate(LocalDateTime.now());
        changeBalance(payment.getCreditAccountId(), payment.getAmount().negate());
        changeBalance(payment.getDebitAccountId(), payment.getAmount());
        payment.setStatus(Status.PAID);
        paymentDao.update(payment);
        return payment;
    }

    public void deletePayment(long id) {
        Payment deletePayment = getPayment(id);
        paymentDao.remove(deletePayment);
    }

    public void changeBalance(long accountId, BigDecimal amount) {
        Account account = accountDao.getById(accountId);
        account.setBalance(account.getBalance().add(amount));
        accountDao.update(account);
    }

    public boolean validateBalance(long accountId, BigDecimal amount) {
        Account account = accountDao.getById(accountId);
        return account.getBalance().compareTo(amount) >= 0;
    }

    public boolean validateCard(long accountId) {
        Card card = cardDao.getByFieldId(accountId);
        return card.getCardCondition().equals(CardCondition.ACTIVE);
    }

    public Payment registrationPayment(long creditCardId, BigDecimal amount, String description, long debitCardNumber) {
        Card card = cardDao.getByNumber(debitCardNumber);
        LOG.info("Card card =" + card);
        if (card == null) {
            return null;
        }
        Payment payment = new Payment(
                LocalDateTime.now(), card.getId(), creditCardId, amount, description, Status.SAVE);
        return paymentDao.create(payment);
    }

    private List<PaymentDTO> mapToPaymentDTO(List<Payment> all) {
        return all.stream().map(payment -> {
            Account accountDebit = accountDao.getById(payment.getDebitAccountId());
            Account accountCredit = accountDao.getById(payment.getCreditAccountId());

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


}
