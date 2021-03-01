package com.project.petbank.repository.impl;


import com.project.petbank.config.ConnectionFactory;
import com.project.petbank.model.Card;
import com.project.petbank.model.enums.CardCondition;
import com.project.petbank.model.enums.CardName;
import com.project.petbank.repository.AbstractDao;
import com.project.petbank.repository.CardDao;
import com.project.petbank.repository.EntityMapper;
import com.project.petbank.repository.GetAllDao;
import org.apache.log4j.Logger;

import java.util.List;

public class CardDaoImpl extends AbstractDao<Card> implements GetAllDao<Card>, CardDao {

    public CardDaoImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    private static final Logger LOG = Logger.getLogger(CardDaoImpl.class);
    private static final String COLUMN_CARD_NAME = "card_name";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_IS_ACTIVE = "card_condition";
    private static final String COLUMN_ACCOUNT_ID = "account_id";
    private static final String SELECT_ALL_CARDS = "SELECT * FROM `card` ";
    private static final String SELECT_ALL_CARDS_PAGINATED = "SELECT * FROM `card` LIMIT ?,?";
    private static final String SELECT_PENDING_CARDS = "SELECT * FROM `card` WHERE card_condition = 'PENDING'";
    private static final String SELECT_NUMBER_CARD = "SELECT * FROM `card` WHERE number =(SELECT MAX(number) FROM `card`) and id>?";

    private static final String INSERT_INTO_CARD = "INSERT INTO `card` ("
            + COLUMN_CARD_NAME + ", "
            + COLUMN_NUMBER + ", "
            + COLUMN_IS_ACTIVE + ", "
            + COLUMN_ACCOUNT_ID + ") VALUE (?, ?, ?, ?)";

    private static final String UPDATE_CARD = "UPDATE `card` SET "
            + COLUMN_CARD_NAME + "= ?, "
            + COLUMN_NUMBER + "= ?, "
            + COLUMN_IS_ACTIVE + "= ?, "
            + COLUMN_ACCOUNT_ID + "= ? WHERE "
            + COLUMN_ID + " = ?";

    private static final String DELETE_CARD = "DELETE FROM `card` "
            + "WHERE " + COLUMN_ID + " = ?";

    @Override
    public List<Card> getPendingCards() {
        LOG.info("CardDaoImpl|getPendingCards()");
        return getAll(SELECT_PENDING_CARDS, getMapper());
    }

    @Override
    public Card getByFieldId(long id) {
        return getByField(SELECT_ALL_CARDS + "WHERE account_id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public Card getByNumber(long number) {
        return getByField(SELECT_ALL_CARDS + "WHERE number = ?",
                ps -> ps.setLong(1, number),
                getMapper());
    }

    @Override
    public Card getActiveCardByFieldId(long id) {
        return getByField(SELECT_ALL_CARDS + "WHERE (card_condition = 'ACTIVE' AND account_id = ?)",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public Card getByNumber(String number) {
        return getByField(SELECT_ALL_CARDS + "WHERE number = ?",
                ps -> ps.setString(1, number),
                getMapper());
    }

    @Override
    public Card findMaxValueByNumber(long id) {
        return getByField(SELECT_NUMBER_CARD,
                ps -> ps.setLong(1, id),
                getMapper());
    }

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
        return getAllByField(SELECT_ALL_CARDS + "WHERE " + COLUMN_CARD_NAME + " = ?",
                ps -> ps.setString(1, field),
                getMapper());
    }

    @Override
    public List<Card> getAllPaginated(int page, int size, String sort, String direction) {
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
    public List<Card> getAllPaginated(long accountId, int page, int size, String sort, String direction) {
        return null;
    }

    @Override
    public Card getById(long id) {
        return getByField(SELECT_ALL_CARDS + "WHERE id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public Card create(Card entity) {
        LOG.debug("Create card: + " + entity);
        long id = super.create(INSERT_INTO_CARD, ps -> {
            ps.setString(1, entity.getCardName().toString());
            ps.setLong(2, entity.getNumber());
            ps.setString(3, entity.getCardCondition().toString());
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
            ps.setLong(2, entity.getNumber());
            ps.setString(3, entity.getCardCondition().toString());
            ps.setLong(4, entity.getAccountId());
            ps.setLong(5, entity.getId());
        });
    }

    @Override
    public boolean remove(Card entity) {
        LOG.debug("Delete card: " + entity);
        return remove(DELETE_CARD, ps -> ps.setLong(1, entity.getId()));
    }

    private EntityMapper<Card> getMapper() {
        return resultSet -> new Card(resultSet.getLong(COLUMN_ID),
                CardName.valueOf(resultSet.getString(COLUMN_CARD_NAME)),
                resultSet.getLong(COLUMN_NUMBER),
                CardCondition.valueOf(resultSet.getString(COLUMN_IS_ACTIVE)),
                resultSet.getLong(COLUMN_ACCOUNT_ID));
    }

}
