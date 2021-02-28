package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.Role;
import com.project.petbank.service.ServiceFactory;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.HOME_PAGE;
import static com.project.petbank.view.PageUrlConstants.PROFILE_PAGE;

public class ProfileCommand extends UniCommand {
    private static final Logger LOG = Logger.getLogger(ProfileCommand.class);
    public static final String ID = "id";
    private UserService userService;

    public ProfileCommand() {
        this.userService = ServiceFactory.getUserService();
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter(ID));
        User userProfile = userService.getUser(id);
        request.setAttribute("userProfile", userProfile);
        return new PageResponse(PROFILE_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        long id = Long.parseLong(userId);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        LOG.debug("confirm Password:" + confirmPassword);
        boolean isActive = request.getParameter("active").equals("true");
        LOG.debug("isActive:" + isActive);
        Role role = Role.valueOf(request.getParameter("role"));
        LOG.debug("Edit user profile firstName: " + firstName + ", lastName" + lastName +
                ", email: " + email + ", password: " + password + "Role" + role);
        if (userService.validateLoginByUpdate(email, id) && userService.validatePassword(password, confirmPassword)) {
            User editUser = userService.updateUser(id, firstName, lastName, email, password, isActive, role);
            LOG.debug("Edit user profile: + " + editUser);
            return new PageResponse(HOME_PAGE, true);
        }
        request.setAttribute("notification", "Not valid login or password");
        return new PageResponse(PROFILE_PAGE + "?id=" + id, true);
    }

}
