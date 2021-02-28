package com.project.petbank.service;

import com.project.petbank.config.DataSourceConnectionPool;
import com.project.petbank.config.transaction.TransactionHandler;
import com.project.petbank.config.transaction.TransactionManager;

public class TransactionHandlerFactory {
    private static final TransactionManager TRANSACTION_MANAGER = new TransactionManager(
            DataSourceConnectionPool.getInstance());

    private static final TransactionHandler TRANSACTION_HANDLER = new TransactionHandler(TRANSACTION_MANAGER);

    public static TransactionHandler getTransactionHandler() {
        return TRANSACTION_HANDLER;
    }

}
