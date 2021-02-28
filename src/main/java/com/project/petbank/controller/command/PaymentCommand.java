package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.model.Payment;
import com.project.petbank.service.CardService;
import com.project.petbank.service.PaymentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static com.project.petbank.view.PageUrlConstants.PAYMENTS_PAGE;
import static com.project.petbank.view.PageUrlConstants.STATEMENTS_PAGE;

public class PaymentCommand extends UniCommand {
    private CardService cardService;
    private PaymentService paymentService;

    private static final Logger LOG = Logger.getLogger(PaymentCommand.class);
    public static final String ID = "userId";

    public PaymentCommand(CardService cardService, PaymentService paymentService) {
        this.cardService = cardService;
        this.paymentService = paymentService;
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter(ID));
        LOG.info("userId =" + userId);
        request.setAttribute("cards", cardService.findAllByUserIdAndCardCondition(userId));
        return new PageResponse(PAYMENTS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {
        long creditCardId = Long.parseLong(request.getParameter("cardId"));
        LOG.info("creditCardId =" + creditCardId);
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        LOG.info("amount =" + amount);
        String description = request.getParameter("description");
        LOG.info("description =" + description);
        long debitCardId = Long.parseLong(request.getParameter("debit"));
        LOG.info("debitCardId =" + debitCardId);
        Payment payment = paymentService.registrationPayment(creditCardId, amount, description, debitCardId);
        LOG.info("long userId = " + payment);
        if (payment == null) {
            long userId = Long.parseLong(request.getParameter("userId"));
            LOG.info("long userId = " + userId);
            request.setAttribute("notification", "Recipients card not found or invalid amount");
            request.setAttribute("cards", cardService.findAllByUserIdAndCardCondition(userId));
            LOG.info("notification : ");
            return new PageResponse(PAYMENTS_PAGE);
        }

        return new PageResponse(STATEMENTS_PAGE + "?accountId=" + creditCardId + "&page=0&size=3&sort=id", true);
    }
}
