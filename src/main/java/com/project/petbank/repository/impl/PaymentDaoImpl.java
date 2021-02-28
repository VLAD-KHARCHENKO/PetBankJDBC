package com.project.petbank.repository.impl;

import com.project.petbank.config.ConnectionFactory;
import com.project.petbank.model.Payment;
import com.project.petbank.model.enums.Status;
import com.project.petbank.repository.AbstractDao;
import com.project.petbank.repository.EntityMapper;
import com.project.petbank.repository.GetAllDao;
import com.project.petbank.repository.PaymentDao;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;

public class PaymentDaoImpl extends AbstractDao<Payment> implements GetAllDao<Payment>, PaymentDao {

    public PaymentDaoImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    private static final Logger LOG = Logger.getLogger(PaymentDaoImpl.class);
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_DEBIT_ID = "debit_account_id";
    private static final String COLUMN_CREDIT_ID = "credit_account_id";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_STATUS = "status";
    private static final String SELECT_ALL_PAYMENTS = "SELECT * FROM `payment` ";
    private static final String SELECT_ALL_PAYMENTS_PAGINATED = "SELECT * FROM `payment` WHERE status = 'PAID'  and (debit_account_id= ? or credit_account_id = ?) ORDER BY %s %s LIMIT ?, ? ";

    private static final String INSERT_INTO_PAYMENT = "INSERT INTO `payment` ("
            + COLUMN_DATE + ", "
            + COLUMN_DEBIT_ID + ", "
            + COLUMN_CREDIT_ID + ", "
            + COLUMN_AMOUNT + ", "
            + COLUMN_DESCRIPTION + ", "
            + COLUMN_STATUS + ") VALUE (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PAYMENT = "UPDATE `payment` SET "
            + COLUMN_DATE + "= ?, "
            + COLUMN_DEBIT_ID + "= ?, "
            + COLUMN_CREDIT_ID + "= ?, "
            + COLUMN_AMOUNT + "= ?, "
            + COLUMN_DESCRIPTION + "= ?, "
            + COLUMN_STATUS + "= ? WHERE "
            + COLUMN_ID + " = ?";

    private static final String DELETE_PAYMENT = "DELETE FROM `payment` "
            + "WHERE " + COLUMN_ID + " = ?";

    @Override
    public List<Payment> findAllSaveByAccountId(long id) {
        return getAll(SELECT_ALL_PAYMENTS + "WHERE status = 'SAVE' and credit_account_id = ?",
                ps -> {
                    ps.setLong(1, id);
                },
                getMapper());
    }

    @Override
    public List<Payment> findAllPaidByAccountId(long id) {
        return getAll(SELECT_ALL_PAYMENTS + "WHERE status = 'PAID'  and (debit_account_id= ? or credit_account_id = ?)",
                ps -> {
                    ps.setLong(1, id);
                    ps.setLong(2, id);
                },
                getMapper());
    }

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
    public List<Payment> getAllPaginated(int page, int size, String sort, String direction) {
        return null;
    }

    @Override
    public List<Payment> getAllPaginated(long accountId, int page, int size, String sort, String direction) {
        LOG.debug("getAllPaginated String sort: " + sort);
        String strSQLQuery = String.format(SELECT_ALL_PAYMENTS_PAGINATED, sort, direction);
        int limit = (page) * size;
        return getAll(strSQLQuery,
                ps -> {
                    ps.setLong(1, accountId);
                    ps.setLong(2, accountId);
                    ps.setInt(3, limit);
                    ps.setInt(4, size);
                },
                getMapper());
    }

    @Override
    public Payment getById(long id) {
        return getByField(SELECT_ALL_PAYMENTS + "WHERE id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public Payment create(Payment entity) {
        LOG.debug("Create payment: + " + entity);
        long id = super.create(INSERT_INTO_PAYMENT, ps -> {
            ps.setTimestamp(1, Timestamp.valueOf(entity.getDate()));
            ps.setLong(2, entity.getDebitAccountId());
            ps.setLong(3, entity.getCreditAccountId());
            ps.setBigDecimal(4, entity.getAmount());
            ps.setString(5, entity.getDescription());
            ps.setString(6, entity.getStatus().toString());
        });
        entity.setId(id);
        return entity;
    }

    @Override
    public boolean update(Payment entity) {
        LOG.debug("Update payment: " + entity);
        return update(UPDATE_PAYMENT, ps -> {
            ps.setTimestamp(1, Timestamp.valueOf(entity.getDate()));
            ps.setLong(2, entity.getDebitAccountId());
            ps.setLong(3, entity.getCreditAccountId());
            ps.setBigDecimal(4, entity.getAmount());
            ps.setString(5, entity.getDescription());
            ps.setString(6, entity.getStatus().toString());
            ps.setLong(7, entity.getId());
        });
    }

    @Override
    public boolean remove(Payment entity) {
        LOG.debug("Delete payment: " + entity);
        return remove(DELETE_PAYMENT, ps -> ps.setLong(1, entity.getId()));
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

}
