package com.project.petbank.config;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection getConnection();
}
