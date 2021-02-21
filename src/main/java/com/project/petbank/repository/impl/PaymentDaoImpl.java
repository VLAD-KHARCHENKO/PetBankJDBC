package com.project.petbank.repository.impl;


import com.project.petbank.config.ConnectionFactory;
import com.project.petbank.model.Card;
import com.project.petbank.model.Payment;
import com.project.petbank.model.enums.Status;
import com.project.petbank.repository.AbstractDao;
import com.project.petbank.repository.EntityMapper;
import com.project.petbank.repository.GetAllDao;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentDaoImpl extends AbstractDao<Payment> implements GetAllDao<Payment> {
    private static final Logger LOG = Logger.getLogger(PaymentDaoImpl.class);

    public PaymentDaoImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_DEBIT_ID = "debit_account_id";
    private static final String COLUMN_CREDIT_ID = "credit_account_id";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_STATUS = "status";
    private static final String SELECT_ALL_PAYMENTS = "SELECT * FROM `payment` ";



    @Override
    public List<Payment> getAll() {
        return getAll(SELECT_ALL_PAYMENTS, getMapper());
    }

    @Override
    public List<Payment> getAllByFieldId(long id) {
        return null;
    }

    @Override
    public List<Payment> getAllByField(String field) {
        return null;
    }

    @Override
    public List<Payment> getAllPaginated(int page, int size) {
        return null;
    }

    public List<Payment> findAllSaveByAccountId(long id) {
        return getAll(SELECT_ALL_PAYMENTS + "WHERE status = 'SAVE' and debit_account_id = ?",
                ps -> {
                    ps.setLong(1, id);
                },
                getMapper());

    }


    public List<Payment>  findAllPayedByAccountId(long id) {
        return getAll(SELECT_ALL_PAYMENTS + "WHERE status = 'PAID'  and (debit_account_id= ? or credit_account_id = ?)",
                ps -> {
                    ps.setLong(1, id);
                    ps.setLong(2, id);
                },
                getMapper());

    }


    private EntityMapper<Payment> getMapper() {
        return resultSet -> new Payment(resultSet.getLong(COLUMN_ID),
                resultSet.getTimestamp(COLUMN_DATE).toLocalDateTime(),
                resultSet.getLong(COLUMN_DEBIT_ID),
                resultSet.getLong(COLUMN_CREDIT_ID),
                resultSet.getBigDecimal(COLUMN_AMOUNT),
                resultSet.getString(COLUMN_DESCRIPTION),
                Status.valueOf(resultSet.getString(COLUMN_STATUS)));
    }

    @Override
    public Payment getById(long id) {
        return null;
    }

    @Override
    public Payment create(Payment entity) {
        return null;
    }

    @Override
    public boolean update(Payment entity) {
        return false;
    }

    @Override
    public boolean remove(Payment entity) {
        return false;
    }
}
