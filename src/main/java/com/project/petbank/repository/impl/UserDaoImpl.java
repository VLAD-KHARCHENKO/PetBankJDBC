package com.project.petbank.repository.impl;


import com.project.petbank.config.ConnectionFactory;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.Role;
import com.project.petbank.repository.AbstractDao;
import com.project.petbank.repository.EntityMapper;
import com.project.petbank.repository.GetAllDao;
import com.project.petbank.repository.UserDao;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao, GetAllDao<User> {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_SECOND_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_IS_ACTIVE = "isActive";
    private static final String COLUMN_ROLE = "role";
    private static final String SELECT_ALL_USERS = "SELECT * FROM `user` ";
    private static final String SELECT_ALL_USERS_PAGINATED = "SELECT * FROM `user` LIMIT ?,?";

    private static final String INSERT_INTO_USER = "INSERT INTO `user` ("
            + COLUMN_FIRST_NAME + ", "
            + COLUMN_SECOND_NAME + ", "
            + COLUMN_EMAIL + ", "
            + COLUMN_PASSWORD + ", "
            + COLUMN_IS_ACTIVE + ", "
            + COLUMN_ROLE + ") VALUE (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER = "UPDATE `user` SET "
            + COLUMN_FIRST_NAME + "= ?, "
            + COLUMN_SECOND_NAME + "= ?, "
            + COLUMN_EMAIL + "= ?, "
            + COLUMN_PASSWORD + "= ?, "
            + COLUMN_IS_ACTIVE + "= ?, "
            + COLUMN_ROLE + "= ? WHERE "
            + COLUMN_ID + " = ?";

    private static final String DELETE_USER = "DELETE FROM `user` "
            + "WHERE " + COLUMN_ID + " = ?";



    @Override
    public boolean isUserExists(String email) {
        String query = "SELECT 1 FROM `user` WHERE " + COLUMN_EMAIL + " = ?";
        return super.checkIfDataExists(query,
                ps -> ps.setString(1, email));
    }

    @Override
    public User getByLogin(String login) {
        return getByField(SELECT_ALL_USERS + "WHERE email = ?",
                ps -> ps.setString(1, login),
                getMapper());
    }

    @Override
    public List<User> getAll() {
        return getAll(SELECT_ALL_USERS, getMapper());
    }

    @Override
    public List<User> getAllByFieldId(long id) {
        return getAllByField(SELECT_ALL_USERS + "WHERE id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<User> getAllByField(String field) {
        return getAllByField(SELECT_ALL_USERS + "WHERE " + COLUMN_ROLE + " = ?",
                ps -> ps.setString(1, field),
                getMapper());
    }

    @Override
    public List<User> getAllPaginated(int page, int size) {
        int limit = (page - 1) * size;
        return getAll(SELECT_ALL_USERS_PAGINATED,
                ps -> {
                    ps.setInt(1, limit);
                    ps.setInt(2, size);
                },
                getMapper());
    }

    @Override
    public User getById(long id) {
        return getByField(SELECT_ALL_USERS + "WHERE id = ?",
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public User create(User entity) {
        LOG.debug("Create user: + " + entity);
        long id = super.create(INSERT_INTO_USER, ps -> {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setBoolean(5, entity.isActive());
            ps.setString(6, entity.getRole().toString());
        });
        entity.setId(id);
        return entity;
    }

    @Override
    public boolean update(User entity) {
        LOG.debug("Update user: " + entity);
        return update(UPDATE_USER, ps -> {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setString(5, String.valueOf(entity.isActive()));
            ps.setString(6, entity.getRole().toString());
            ps.setLong(6, entity.getId());
        });
    }

    @Override
    public boolean remove(User entity) {
        LOG.debug("Delete user: " + entity);
        return remove(DELETE_USER, ps -> ps.setLong(1, entity.getId()));
    }

    private EntityMapper<User> getMapper() {
        return resultSet -> new User(resultSet.getLong(COLUMN_ID),
                resultSet.getString(COLUMN_FIRST_NAME),
                resultSet.getString(COLUMN_SECOND_NAME),
                resultSet.getString(COLUMN_EMAIL),
                resultSet.getString(COLUMN_PASSWORD),
                resultSet.getBoolean(COLUMN_IS_ACTIVE),
                Role.valueOf(resultSet.getString(COLUMN_ROLE)));
    }
}
