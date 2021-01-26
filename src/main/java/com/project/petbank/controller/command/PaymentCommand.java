package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;

import com.project.petbank.service.UserService;

import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.*;


public class PaymentCommand extends UniCommand {
    private UserService userService;
    private static final Logger LOG = Logger.getLogger(PaymentCommand.class);

    public PaymentCommand(UserService userService) {
        this.userService = userService;
    }

    public PaymentCommand() {

    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        return new PageResponse(PAYMENTS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}