package com.project.petbank.repository;


import com.project.petbank.model.User;

public interface UserDao {

    boolean isUserExists(String login);

    User getByLogin(String login);

}
