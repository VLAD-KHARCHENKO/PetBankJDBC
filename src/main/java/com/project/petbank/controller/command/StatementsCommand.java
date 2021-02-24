package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.service.CardService;
import com.project.petbank.service.PaymentService;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.STATEMENTS_PAGE;


public class StatementsCommand extends UniCommand {
    private UserService userService;
    private PaymentService paymentService;
    private CardService cardService;
    private static final Logger LOG = Logger.getLogger(StatementsCommand.class);
    public static final String ID = "accountId";

    public StatementsCommand(UserService userService, PaymentService paymentService, CardService cardService) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.cardService = cardService;
    }

    public StatementsCommand() {

    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        Long accountId = Long.parseLong(request.getParameter(ID));
        request.setAttribute("savedPayments", paymentService.getSavedPaymentByAccountNumber(accountId));
        request.setAttribute("paidPayments", paymentService.getPaidPaymentByAccountNumber(accountId));

        request.setAttribute("card", cardService.getCardByAccountId(accountId));
        return new PageResponse(STATEMENTS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {
        long paymentId = Long.parseLong(request.getParameter("payId"));
        long cardId = Long.parseLong(request.getParameter("cardId"));
        String command = request.getParameter("command");
        LOG.info("command=" + command);

        switch (command) {
            case ("delete"):
                paymentService.deletePayment(paymentId);

                break;

            case ("pay"):
                paymentService.submitPayment(paymentId);

                break;


        }
        return new PageResponse(STATEMENTS_PAGE + "?accountId=" + cardId, true);
    }
}