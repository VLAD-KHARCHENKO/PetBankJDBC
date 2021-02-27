package com.project.petbank.service;


import com.project.petbank.model.User;
import com.project.petbank.model.enums.Role;
import com.project.petbank.repository.impl.UserDaoImpl;
import com.project.petbank.utils.PasswordsUtil;
import com.project.petbank.view.UserDTO;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);
    private UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

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

    public boolean validateLoginByUpdate(String login, long id) {
        if (userDao.isUserExists(login)) {
            LOG.info(userDao.getById(id).equals(userDao.getByLogin(login)));
            return userDao.getById(id).equals(userDao.getByLogin(login));
        }
        return true;
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
     * Validates whether User is active
     *
     * @param login
     * @return
     */
    public boolean validateUserActive(String login) {
        User user = userDao.getByLogin(login);
        return user.isActive();
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


    public User registrationUser(String firstName, String lastName, String email, String password) {
        String hashedPass = PasswordsUtil.hash(password.trim());
        User newUser = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(hashedPass)
                .isActive(true)
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

    public List<UserDTO> getAllPaginated(int page, int size, String sort, String currentDirection) {
        LOG.info("getAllPaginated:");
        String direction = currentDirection.equals("asc") ? "ASC" : "DESC";
        List<User> all = userDao.getAllPaginated(page, size, sort, direction);
        LOG.info("Get all users:" + all);
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
            UserDTO userDTO = new UserDTO(
                    userProfile.getId(),
                    userProfile.getFirstName(),
                    userProfile.getLastName(),
                    userProfile.getEmail(),
                    userProfile.isActive() ? "Active" : "Blocked",
                    userProfile.getRole()
            );

            return userDTO;
        }).collect(Collectors.toList());
    }

    public User updateUser(long id, String firstName, String lastName, String email, String password, boolean active, Role role) {
        User updatedUser = new User(id, firstName, lastName, email, password, active, role);
        userDao.update(updatedUser);
        return updatedUser;
    }

    public void deleteUser(int id) {
        User deleteUser = getUser(id);
        userDao.remove(deleteUser);
    }
}
