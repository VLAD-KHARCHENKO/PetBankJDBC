package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.PAYMENTS_PAGE;
import static com.project.petbank.view.PageUrlConstants.STATEMENTS_PAGE;


public class StatementsCommand extends UniCommand {
    private UserService userService;
    private static final Logger LOG = Logger.getLogger(StatementsCommand.class);

    public StatementsCommand(UserService userService) {
        this.userService = userService;
    }

    public StatementsCommand() {

    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        return new PageResponse(STATEMENTS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}