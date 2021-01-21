package com.project.petbank.repository;


import com.project.petbank.config.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractDao<T> implements CrudDao<T> {
    private static final Logger LOG = Logger.getLogger(AbstractDao.class);

    private final ConnectionFactory connectionFactory;

    protected Connection getConnection() {
        return connectionFactory.getConnection();
    }

    protected static final String COLUMN_ID = "id";

    public List<T> getAll(String query, EntityMapper<T> mapper) {
        return getAll(query, null, mapper);
    }

    public List<T> getAll(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        Connection conn = getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            if (statementMapper != null) {
                statementMapper.map(preparedStatement);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T entity = mapper.map(resultSet);
                    result.add(entity);
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception while getting all entities", e);
            throw new RuntimeException(e);
        } finally {
            closeAutocommitConnection(conn);
        }

        return result;
    }

    public T getByField(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        T result = null;
        Connection conn = getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            statementMapper.map(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception while getting all entities", e);
        }

        return result;
    }

    public List<T> getAllByField(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        Connection conn = getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            statementMapper.map(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    T entity = mapper.map(resultSet);
                    result.add(entity);
                }
            }

        } catch (SQLException e) {
            LOG.error("Exception while getting all entities", e);
        }

        return result;
    }

    public boolean checkIfDataExists(String query, StatementMapper<T> statementMapper) {
        int result = 0;

        Connection conn = getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            statementMapper.map(preparedStatement);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("1");
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception while try to find match.", e);
        } finally {
            closeAutocommitConnection(conn);
        }
        LOG.info("Match result=" + result);
        return result == 1;
    }

    public long create(String query, StatementMapper<T> statementMapper) {
        Connection conn = getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statementMapper.map(preparedStatement);

            long result = preparedStatement.executeUpdate();
            if (result != 1) {
                LOG.error("Could not create entity.");
                return -1;
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    LOG.info("Id for new entity record id=" + generatedId);
                    return generatedId;
                } else {
                    LOG.error("Creating entity failed, no ID obtained.");
                    throw new RuntimeException("Creating entity failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOG.error("Could not create entity!!", e);
            throw new RuntimeException(e);
        } finally {
            closeAutocommitConnection(conn);
        }
    }

    public boolean update(String query, StatementMapper<T> statementMapper) {
        try {
            return updateRemove(query, statementMapper);
        } catch (SQLException e) {
            LOG.error("Could not update entity.", e);
            throw new RuntimeException(e);
        }
    }

    public boolean remove(String query, StatementMapper<T> statementMapper) {
        try {
            return updateRemove(query, statementMapper);
        } catch (SQLException e) {
            LOG.error("Could not remove entity.", e);
            throw new RuntimeException(e);
        }
    }

    private boolean updateRemove(String query, StatementMapper<T> statementMapper) throws SQLException {
        Connection conn = getConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            statementMapper.map(preparedStatement);

            int result = preparedStatement.executeUpdate();
            return result == 1;
        } finally {
            closeAutocommitConnection(conn);
        }
    }

    protected void closeAutocommitConnection(Connection connection) {
        try {
            if (connection != null && connection.getAutoCommit()) {
                LOG.error("Connection closed");
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("Could not close connection.", e);
        }
    }
}
