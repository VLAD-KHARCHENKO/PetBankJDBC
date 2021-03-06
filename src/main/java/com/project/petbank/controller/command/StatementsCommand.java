package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.service.CardService;
import com.project.petbank.service.PaymentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.STATEMENTS_PAGE;
import static java.util.Objects.isNull;

public class StatementsCommand extends UniCommand {
    private PaymentService paymentService;
    private CardService cardService;
    private static final Logger LOG = Logger.getLogger(StatementsCommand.class);
    public static final String ID = "accountId";

    public StatementsCommand(PaymentService paymentService, CardService cardService) {
        this.paymentService = paymentService;
        this.cardService = cardService;
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        Long accountId = Long.parseLong(request.getParameter(ID));
        request.setAttribute("savedPayments", paymentService.getSavedPaymentByAccountNumber(accountId));
        request.setAttribute("card", cardService.getCardByAccountId(accountId));
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        String sort = request.getParameter("sort");
        String currentDirection = request.getParameter("direction");
        if (currentDirection == null) {
            currentDirection = "asc";
        }
        int size;
        int page;
        if (isNull(pageStr)) {
            page = 0;
        } else {
            page = Integer.parseInt(pageStr);
        }
        if (isNull(sizeStr)) {
            size = 3;
        } else {
            size = Integer.parseInt(sizeStr);
        }

        LOG.info("page=" + page + " size=" + size + " sort= " + sort + " currentDirection= " + currentDirection);
        request.setAttribute("paidPayments", paymentService.getAllPaginated(accountId, page, size, sort, currentDirection));
        LOG.info("Set paidPayment");
        request.setAttribute("currentPage", page);
        LOG.info("Set currentPage=" + page);
        request.setAttribute("paidPaymentsPages", (int) Math.ceil((double) paymentService.getPaidPaymentByAccountNumber(accountId).size() / (double) size));
        request.setAttribute("currentDirection", currentDirection);
        LOG.info("Set currentDirection=" + currentDirection);
        request.setAttribute("direction", currentDirection.equals("asc") ? "desc" : "asc");
        LOG.info("Set direction=" + (currentDirection.equals("asc") ? "desc" : "asc"));
        request.setAttribute("sort", sort);
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
