package com.project.petbank.config;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnectionPool implements ConnectionFactory {
    private static Logger LOG = Logger.getLogger(com.project.petbank.config.DataSourceConnectionPool.class);
    private static final com.project.petbank.config.DataSourceConnectionPool INSTANCE = new com.project.petbank.config.DataSourceConnectionPool();
    private static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/petbank");
        } catch (NamingException e) {
            LOG.error("Could not find DataSource JNDI", e);
        }
    }

    private DataSourceConnectionPool() {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            LOG.debug("Connection received " + connection + " " + connection.hashCode());
        } catch (SQLException e) {
            LOG.error("Some problem was occurred while getting connection to BD", e);
        }
        return connection;
    }

    public static com.project.petbank.config.DataSourceConnectionPool getInstance() {
        return INSTANCE;
    }
}
