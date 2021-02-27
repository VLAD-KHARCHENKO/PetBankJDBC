package com.repairagency.service;

import com.project.petbank.model.User;
import com.project.petbank.repository.AbstractDao;
import com.project.petbank.service.UserService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;

    private AbstractDao<User> userDao = (AbstractDao<User>) mock(AbstractDao.class);



    @Test
    public void validatePassword() {
        String password = "123";
        String confirmPassword= "123";
        boolean result;
        result = userService.validatePassword(password, confirmPassword);
        assertTrue(result);
    }


}
