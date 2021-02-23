package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;

import com.project.petbank.service.CardService;
import com.project.petbank.service.UserService;

import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.*;


public class PaymentCommand extends UniCommand {
    private UserService userService;
    CardService cardService;
    private static final Logger LOG = Logger.getLogger(PaymentCommand.class);
    public static final String ID = "userId";
    public PaymentCommand(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    public PaymentCommand() {

    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter(ID));
        LOG.info("userId ="+userId);
       request.setAttribute("cards", cardService.findAllByUserIdAndCardCondition(userId));
        return new PageResponse(PAYMENTS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}