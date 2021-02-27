package com.project.petbank.service;

import com.project.petbank.repository.DaoFactory;

public class ServiceFactory {
    private ServiceFactory() {
    }


    private static UserService userService = new UserService(
            DaoFactory.getUserDao()
    );
    private static CardService cardService = new CardService(
            DaoFactory.getCardDao(),
            DaoFactory.getAccountDao(),
            DaoFactory.getUserDao(),
            ServiceFactory.getUserService()

    );
    private static AccountService accountService = new AccountService(
            DaoFactory.getAccountDao()
    );

    private static PaymentService paymentService = new PaymentService(
            DaoFactory.getCardDao(),
            DaoFactory.getPaymentDao(),
            DaoFactory.getAccountDao(),
            TransactionHandlerFactory.getTransactionHandler()
    );


    public static UserService getUserService() {
        return userService;
    }
    public static CardService getCardService() {
        return cardService;
    }
    public static AccountService getAccountService() {
        return accountService;
    }
    public static PaymentService getPaymentService(){return paymentService;}


}
