package com.project.petbank.service;

import com.project.petbank.model.User;
import com.project.petbank.model.enums.Role;
import com.project.petbank.repository.impl.UserDaoImpl;
import com.project.petbank.utils.PasswordsUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService instance;
    private UserDaoImpl userDao = mock(UserDaoImpl.class);
    private User user;

    @Before
    public void setUp() {
        instance = new UserService(userDao);
        long id = 1L;
        String firsName = "John";
        String lastName = "Johnenko";
        String email = "e@mail";
        String password = "password";
        String hashedPass = PasswordsUtil.hash(password.trim());
        user = new User(id, firsName, lastName, email, hashedPass, true, Role.CUSTOMER);
    }

    @Test
    public void shouldAddValidUser() {
        instance.registrationUser("john", "Johnenko", "e@mail", "password");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDao).create(userCaptor.capture());
        User actualUser = userCaptor.getValue();

        assertThat(actualUser.getFirstName()).isEqualTo("john");
        assertThat(actualUser.getLastName()).isEqualTo("Johnenko");
        assertThat(actualUser.getEmail()).isEqualTo("e@mail");
        assertThat(actualUser.getPassword()).isNotBlank();
        assertThat(actualUser.getRole()).isEqualTo(Role.CUSTOMER);

    }

    @Test
    public void validateUserSuccess() {

        String email = "e@mail";
        String password = "password";

        when(userDao.getByLogin(anyString())).thenReturn(user);
        assertTrue(instance.validateUser(email, password));
    }

    @Test
    public void validateUserError() {

        String wrongEmail = "wrong@mail";
        String password = "password";
        when(userDao.getByLogin(anyString())).thenReturn(user);

        assertFalse(instance.validateUser(password, wrongEmail));

    }

    @Test
    public void ShouldValidateUserIsActive() {
        String email = "e@mail";
        when(userDao.getByLogin(anyString())).thenReturn(user);
        assertTrue(instance.validateUserActive(email));
    }

    @Test
    public void shouldReturnTrueIfUserExist() {
        String login = "login";
        when(userDao.isUserExists(anyString())).thenReturn(true);
        assertFalse(instance.validateLogin(login));
    }

    @Test
    public void shouldReturnFalseIfUserNotExist() {
        String login = "login";
        when(userDao.isUserExists(anyString())).thenReturn(false);
        assertTrue(instance.validateLogin(login));
    }

    @Test
    public void shouldReturnTrueIfValidPassword() {
        String password = "password";
        String confirmPassword = "password";
        assertTrue(instance.validatePassword(password, confirmPassword));
    }

    @Test
    public void shouldReturnFalseIfInvalidPassword() {
        String password = "password";
        String confirmPassword = "wrongPassword";
        assertFalse(instance.validatePassword(password, confirmPassword));
    }

    @Test
    public void shouldReturnUserById() {
        when(userDao.getById(anyLong())).thenReturn(user);
        assertEquals(instance.getUser(1), user);
    }

    @Test
    public void shouldReturnUserByLogin() {
        String email = "e@mail";
        when(userDao.getByLogin(anyString())).thenReturn(user);
        assertEquals(instance.getUserByLogin(email), user);
    }

    @Test
    public void shouldRegistrationUser() {
        String firsName = "John";
        String lastName = "Johnenko";
        String email = "e@mail";
        String password = "password";

        when(userDao.create(anyObject())).thenReturn(user);
        User actualUser = instance.registrationUser(firsName, lastName, email, password);

        assertEquals(actualUser.getFirstName(), firsName);
        assertEquals(actualUser.getLastName(), lastName);
        assertEquals(actualUser.getEmail(), email);
        assertTrue(PasswordsUtil.verifyHash(password, user.getPassword()));
    }

}
