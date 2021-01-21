package com.project.petbank.service;


import com.project.petbank.model.User;
import com.project.petbank.model.enums.Role;
import com.project.petbank.repository.impl.UserDaoImpl;
import com.project.petbank.utils.PasswordsUtil;
import com.project.petbank.view.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);
    private UserDaoImpl userDao;

    /**
     * Validates User's Login and checks if it corresponds with password
     *
     * @param login
     * @param password
     * @return
     */
    public boolean validateUser(String login, String password) {
        User user = userDao.getByLogin(login);
        LOG.info("Get user by login:" + user);
        if (user != null && PasswordsUtil.verifyHash(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * Validates if Login exists in DB
     *
     * @param login
     * @return
     */
    public boolean validateLogin(String login) {
        return !userDao.isUserExists(login);
    }

    /**
     * Validates whether confirmPasswords corresponds with Password
     *
     * @param password
     * @param confirmPassword
     * @return
     */
    public boolean validatePassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) return true;
        return false;
    }

    /**
     * Gets User by ID from DB
     *
     * @param id
     * @return
     */
    public User getUser(long id) {
        return userDao.getById(id);
    }

    /**
     * Gets User by Login from DB
     *
     * @param login
     * @return
     */
    public User getUserByLogin(String login) {
        return userDao.getByLogin(login);
    }

    /**
     * Converts data from Post request to User and stores it into DB
     *
     * @param name
     * @param login
     * @param password
     * @return
     */
    public User registrationUser(String firstName, String lastName, String email, String password, boolean isActive, Role role) {
        String hashedPass = PasswordsUtil.hash(password.trim());
        User newUser = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .isActive(isActive)
                .role(Role.CUSTOMER)
                .build();
        userDao.create(newUser);
        return newUser;
    }

    /**
     * Gets List UserDTO from DB
     *
     * @return
     */
    public List<UserDTO> getAll() {
        List<User> all = userDao.getAll();
        return mapToUserDTO(all);
    }

    /**
     * Adds List User to List UserDTO
     *
     * @param all
     * @return
     */
    private List<UserDTO> mapToUserDTO(List<User> all) {
        return all.stream().map(users -> {
            User userProfile = userDao.getById(users.getId());
            UserDTO userDTO = UserDTO.builder()
                    .id(userProfile.getId())
                    .firstName(userProfile.getFirstName())
                    .lastName(userProfile.getLastName())
                    .email(userProfile.getEmail())
                    .password(userProfile.getPassword())
                    .isActive(userProfile.isActive())
                    .role(userProfile.getRole())
                    .build();

            return userDTO;
        }).collect(Collectors.toList());
    }
}
