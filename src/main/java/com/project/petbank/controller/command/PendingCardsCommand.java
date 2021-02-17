package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.service.CardService;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.PENDING_PAGE;
import static com.project.petbank.view.PageUrlConstants.USERS_PAGE;
import static java.util.Objects.isNull;


public class PendingCardsCommand extends UniCommand {
  private CardService cardService;
    private static final Logger LOG = Logger.getLogger(PendingCardsCommand.class);

    public PendingCardsCommand(CardService cardService) {
        this.cardService = cardService;
    }

    public PendingCardsCommand() {

    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
//        String pageStr = request.getParameter("page");
//        String sizeStr = request.getParameter("size");
//        Integer size;
//        Integer page;
//        if (isNull(pageStr)) {
//            page = 1;
//        } else {
//            page = Integer.parseInt(pageStr);
//        }
//        if (isNull(sizeStr)) {
//            size = 30;
//        } else {
//            size = Integer.parseInt(sizeStr);
//        }
//        LOG.info("page="+page+" size="+size);


       request.setAttribute("pendingCards", cardService.getPendingCard());
        LOG.info("Set pendingCards");

        return new PageResponse(PENDING_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}