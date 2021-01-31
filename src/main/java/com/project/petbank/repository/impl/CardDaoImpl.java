package com.project.petbank.repository.impl;


import com.project.petbank.config.ConnectionFactory;
import com.project.petbank.model.Card;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.CardName;
import com.project.petbank.repository.AbstractDao;
import com.project.petbank.repository.EntityMapper;
import com.project.petbank.repository.GetAllDao;
import com.project.petbank.repository.UserDao;
import org.apache.log4j.Logger;

import java.util.List;

public class CardDaoImpl extends AbstractDao<Card> implements GetAllDao<Card> {
    private static final Logger LOG = Logger.getLogger(CardDaoImpl.class);

    public CardDaoImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    private static final String COLUMN_СARD_NAME = "card_name";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_IS_ACTIVE = "isActive";
    private static final String COLUMN_ACCOUNT_ID = "account_id";
    private static final String SELECT_ALL_CARDS = "SELECT * FROM `card` ";
    private static final String SELECT_ALL_CARDS_PAGINATED = "SELECT * FROM `card` LIMIT ?,?";
    //  private static final String SELECT_ALL_FOR_CARD = "SELECT card_name, isActive, number"+
    //   "FROM `card` JOIN `account` ON card.account_id = account.id";

    private static final String INSERT_INTO_CARD = "INSERT INTO `card` ("
            + COLUMN_СARD_NAME + ", "
            + COLUMN_NUMBER + ", "
            + COLUMN_IS_ACTIVE + ", "
            + COLUMN_ACCOUNT_ID + ") VALUE (?, ?, ?)";

    private static final String UPDATE_CARD = "UPDATE `card` SET "
            + COLUMN_СARD_NAME + "= ?, "
            + COLUMN_NUMBER + "= ?, "
            + COLUMN_IS_ACTIVE + "= ?, "
            + COLUMN_ACCOUNT_ID + "= ? WHERE "
            + COLUMN_ID + " = ?";

    private static final String DELETE_CARD = "DELETE FROM `card` "
            + "WHERE " + COLUMN_ID + " = ?";


    @Override
    public List<Card> getAll() {
        return getAll(SELECT_ALL_CARDS, getMapper());
    }

    @Override
    public List<Card> getAllByFieldId(long id) {
        return getAllByField(SELECT_ALL_CARDS + "WHERE id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<Card> getAllByField(String field) {
        return getAllByField(SELECT_ALL_CARDS + "WHERE " + COLUMN_СARD_NAME + " = ?",
                ps -> ps.setString(1, field),
                getMapper());
    }

    @Override
    public List<Card> getAllPaginated(int page, int size) {
        LOG.debug("getAllPaginated : ");
        int limit = (page - 1) * size;
        return getAll(SELECT_ALL_CARDS_PAGINATED,
                ps -> {
                    ps.setInt(1, limit);
                    ps.setInt(2, size);
                },
                getMapper());
    }

    @Override
    public Card getById(long id) {
        return getByField(SELECT_ALL_CARDS + "WHERE id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }


    public Card getByNumber(String number) {
        return getByField(SELECT_ALL_CARDS + "WHERE number = ?",
                ps -> ps.setString(1, number),
                getMapper());
    }

    @Override
    public Card create(Card entity) {
        LOG.debug("Create card: + " + entity);
        long id = super.create(INSERT_INTO_CARD, ps -> {
            ps.setString(1, entity.getCardName().toString());
            ps.setString(2, entity.getNumber());
            ps.setBoolean(3, entity.isActive());
            ps.setLong(4, entity.getAccountId());
        });
        entity.setId(id);
        return entity;
    }

    @Override
    public boolean update(Card entity) {
        LOG.debug("Update card: " + entity);
        return update(UPDATE_CARD, ps -> {
            ps.setString(1, entity.getCardName().toString());
            ps.setString(2, entity.getNumber());
            ps.setBoolean(3, entity.isActive());
            ps.setLong(4, entity.getAccountId());
            ps.setLong(6, entity.getId());
        });
    }

    @Override
    public boolean remove(Card entity) {
        LOG.debug("Delete card: " + entity);
        return remove(DELETE_CARD, ps -> ps.setLong(1, entity.getId()));
    }

    private EntityMapper<Card> getMapper() {
        return resultSet -> new Card(resultSet.getLong(COLUMN_ID),
                CardName.valueOf(resultSet.getString(COLUMN_СARD_NAME)),
                resultSet.getString(COLUMN_NUMBER),
                resultSet.getBoolean(COLUMN_IS_ACTIVE),
                resultSet.getLong(COLUMN_ACCOUNT_ID));
    }
}
