package com.project.petbank.repository.impl;


import com.project.petbank.config.ConnectionFactory;
import com.project.petbank.model.Account;
import com.project.petbank.repository.AbstractDao;
import com.project.petbank.repository.EntityMapper;
import com.project.petbank.repository.GetAllDao;
import org.apache.log4j.Logger;

import java.util.List;

public class AccountDaoImpl extends AbstractDao<Account> implements GetAllDao<Account> {
    private static final Logger LOG = Logger.getLogger(AccountDaoImpl.class);

    public AccountDaoImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_BALANCE = "balance";
    private static final String COLUMN_IS_ACTIVE = "isActive";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String SELECT_ALL_ACCOUNT = "SELECT * FROM `account` ";
    private static final String SELECT_PAGINATE_ACCOUNT = "SELECT * FROM `account` LIMIT ?,?";

    //  private static final String SELECT_ALL_FOR_CARD = "SELECT card_name, isActive, number"+
    //   "FROM `card` JOIN `account` ON card.account_id = account.id";

    private static final String INSERT_INTO_ACCOUNT = "INSERT INTO `account` ("
            + COLUMN_NUMBER + ", "
            + COLUMN_BALANCE + ", "
            + COLUMN_IS_ACTIVE + ", "
            + COLUMN_USER_ID + ") VALUE (?, ?, ?)";

    private static final String UPDATE_ACCOUNT = "UPDATE `account` SET "
            + COLUMN_NUMBER + "= ?, "
            + COLUMN_IS_ACTIVE + "= ?, "
            + COLUMN_USER_ID + "= ? WHERE "
            + COLUMN_ID + " = ?";

    private static final String DELETE_ACCOUNT = "DELETE FROM `account` "
            + "WHERE " + COLUMN_ID + " = ?";


    @Override
    public List<Account> getAll() {
        return getAll(SELECT_ALL_ACCOUNT, getMapper());
    }

    @Override
    public List<Account> getAllByFieldId(long id) {
        return getAllByField(SELECT_ALL_ACCOUNT + "WHERE user_id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<Account> getAllByField(String field) {
        return getAllByField(SELECT_ALL_ACCOUNT + "WHERE " + COLUMN_IS_ACTIVE + " = ?",
                ps -> ps.setString(1, field),
                getMapper());
    }

    @Override
    public List<Account> getAllPaginated(int page, int size) {
        LOG.debug("getAllPaginated : ");
        int limit = (page - 1) * size;
        return getAll(SELECT_PAGINATE_ACCOUNT,
                ps -> {
                    ps.setInt(1, limit);
                    ps.setInt(2, size);
                },
                getMapper());
    }

    @Override
    public Account getById(long id) {
        return getByField(SELECT_ALL_ACCOUNT + "WHERE id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }


    public Account getByNumber(String number) {
        return getByField(SELECT_ALL_ACCOUNT + "WHERE number = ?",
                ps -> ps.setString(1, number),
                getMapper());
    }

    @Override
    public Account create(Account entity) {
        LOG.debug("Create account: + " + entity);
        long id = super.create(INSERT_INTO_ACCOUNT, ps -> {
            ps.setString(1, entity.getNumber());
            ps.setBigDecimal(2, entity.getBalance());
            ps.setBoolean(3, entity.isActive());
            ps.setLong(4, entity.getUserId());
        });
        entity.setId(id);
        return entity;
    }

    @Override
    public boolean update(Account entity) {
        LOG.debug("Update account: " + entity);
        return update(UPDATE_ACCOUNT
                , ps -> {
                    ps.setString(1, entity.getNumber());
                    ps.setBigDecimal(2, entity.getBalance());
                    ps.setBoolean(3, entity.isActive());
                    ps.setLong(4, entity.getUserId());
                });
    }

    @Override
    public boolean remove(Account entity) {
        LOG.debug("Delete user: " + entity);
        return remove(DELETE_ACCOUNT, ps -> ps.setLong(1, entity.getId()));
    }

    private EntityMapper<Account> getMapper() {
        return resultSet -> new Account(resultSet.getLong(COLUMN_ID),
                resultSet.getString(COLUMN_NUMBER),
                resultSet.getBigDecimal(COLUMN_BALANCE),
                resultSet.getBoolean(COLUMN_IS_ACTIVE),
                resultSet.getLong(COLUMN_USER_ID));
    }
}
