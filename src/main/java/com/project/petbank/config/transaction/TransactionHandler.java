package com.project.petbank.config.transaction;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class TransactionHandler {
    private final TransactionManager transactionManager;

    public <T> T runInTransaction(Supplier<T> supplier) {
        transactionManager.begin();
        try {
            supplier.get();
            transactionManager.commit();
        } catch (Throwable e) {
            transactionManager.rollback();
            throw e;
        }
        return supplier.get();
    }

    public void runInTransaction(Runnable runnable) {
        transactionManager.begin();
        try {
            runnable.run();
            transactionManager.commit();
        } catch (Throwable e) {
            transactionManager.rollback();
            throw e;
        }
    }
}
