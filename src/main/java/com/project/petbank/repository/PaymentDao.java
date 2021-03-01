package com.project.petbank.repository;

import com.project.petbank.model.Payment;

import java.util.List;

public interface PaymentDao {

    List<Payment> findAllSaveByAccountId(long id);

    List<Payment> findAllPaidByAccountId(long id);

}
