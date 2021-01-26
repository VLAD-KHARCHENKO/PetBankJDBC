package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.CARDS_PAGE;


public class CardsCommand extends UniCommand {
    private UserService userService;
    private static final Logger LOG = Logger.getLogger(CardsCommand.class);

    public CardsCommand(UserService userService) {
        this.userService = userService;
    }

    public CardsCommand() {
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        return new PageResponse(CARDS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}