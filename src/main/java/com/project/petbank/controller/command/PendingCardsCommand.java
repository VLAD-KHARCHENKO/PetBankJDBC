package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.model.enums.CardCondition;
import com.project.petbank.service.CardService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.PENDING_PAGE;

public class PendingCardsCommand extends UniCommand {
    private CardService cardService;
    private static final Logger LOG = Logger.getLogger(PendingCardsCommand.class);

    public PendingCardsCommand(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        LOG.info("Pending Cards Command");
        request.setAttribute("pendingCards", cardService.getPendingCard());
        LOG.info("Set pendingCards");

        return new PageResponse(PENDING_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {
        LOG.info("Activate Cards Command");
        long id = Long.parseLong(request.getParameter("cardId"));
        LOG.info("ID" + id);
        cardService.changeConditionCard(id, CardCondition.ACTIVE);
        return new PageResponse(PENDING_PAGE, true);
    }

}
