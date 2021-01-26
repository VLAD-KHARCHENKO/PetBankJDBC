package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.Role;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.project.petbank.view.PageUrlConstants.*;



public class LoginCommand extends UniCommand {
    private UserService userService;
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        return new PageResponse(LOGIN_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        HttpSession session = request.getSession();

        if (userService.validateUser(email, password)) {
            User user = userService.getUserByLogin(email);
            LOG.info("get user by login" + user);
            session.setAttribute("user", user);
            if (user.getRole() == Role.ADMIN) {
                return new PageResponse( ADMIN_PAGE, true);
            } else if (user.getRole() == Role.CUSTOMER) {
                return new PageResponse( USER_PAGE, true);
            } else {
                return new PageResponse(HOME_PAGE, true);
            }
        }
        request.setAttribute("notification", "Login or password invalid!");
        return new PageResponse(LOGIN_PAGE, false);

    }
}
