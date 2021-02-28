package com.project.petbank.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceConnectionFactory implements ConnectionFactory {
    private static final Logger LOG = Logger.getLogger(DataSourceConnectionFactory.class);
    private static final DataSourceConnectionFactory INSTANCE = new DataSourceConnectionFactory();

    private static DataSource dataSource;

    private DataSourceConnectionFactory() {
    }

    static {
        Properties properties = new Properties();
        try {
            properties.load(DataSourceConnectionFactory.class.getResourceAsStream("/db.properties"));
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl(properties.getProperty("DB_URL"));
            mysqlDataSource.setUser(properties.getProperty("DB_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("DB_PASSWORD"));
            dataSource = mysqlDataSource;
            LOG.info("Datasource created: " + dataSource);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public DataSourceConnectionFactory getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
