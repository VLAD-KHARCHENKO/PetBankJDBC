package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.model.User;
import com.project.petbank.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.project.petbank.view.PageUrlConstants.HOME_PAGE;
import static com.project.petbank.view.PageUrlConstants.REGISTER_PAGE;


@AllArgsConstructor
public class RegisterCommand extends UniCommand {

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);
    private UserService userService;

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        return new PageResponse(REGISTER_PAGE);
    }

   @Override
   protected PageResponse performPost(HttpServletRequest request) {
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        String password = request.getParameter("password");
//        String confirmPassword = request.getParameter("confirmPassword");
       //    System.out.println("Registration firstName: " + firstName + "Registration lastName: " + lastName +
       //             ", email: " + email + ", password: " + password);

//        HttpSession session = request.getSession();
//
//        if (userService.validateLogin(email) && userService.validatePassword(password, confirmPassword)) {
//            User newUser = userService.registrationUser(firstName, email, password);
//            LOG.debug("registration user: + " + newUser);
//
//            User user = userService.getUserByLogin(email);
//            session.setAttribute("user", user);
//            LOG.info("registration user setAttribute: + " + user);
//            return new PageResponse(HOME_PAGE, true);
//        }
//        request.setAttribute("notification", "Not valid login or password");
//        return new PageResponse(REGISTER_PAGE);
return null;
   }

}
