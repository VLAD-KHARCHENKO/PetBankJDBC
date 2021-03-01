package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.model.User;
import com.project.petbank.model.enums.CardCondition;
import com.project.petbank.model.enums.CardName;
import com.project.petbank.service.CardService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static com.project.petbank.view.PageUrlConstants.CARDS_PAGE;

public class CardsCommand extends UniCommand {
    private CardService cardService;
    private static final Logger LOG = Logger.getLogger(CardsCommand.class);
    public static final String ID = "userId";

    public CardsCommand(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter(ID));
        request.setAttribute("cards", cardService.getAllByUserId(userId));
        request.setAttribute("cardName", CardName.values());
        request.setAttribute("activeCards", cardService.findAllByUserIdAndCardCondition(userId));
        LOG.info("Set cards");
        return new PageResponse(CARDS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userId = request.getParameter("userId");
        LOG.info("userId=" + userId);
        if (userId == null) {
            userId = String.valueOf(user.getId());
        }

        String command = request.getParameter("command");
        LOG.info("command=" + command);

        switch (command) {
            case ("activate"):
                cardService.changeConditionCard(parseParameterCardId(request), CardCondition.ACTIVE);
                break;
            case ("blocked"):
                cardService.changeConditionCard(parseParameterCardId(request), CardCondition.BLOCKED);
                break;
            case ("pending"):
                cardService.changeConditionCard(parseParameterCardId(request), CardCondition.PENDING);
                break;
            case ("newCard"):
                cardService.registrationCard(parseParameterCardName(request), user);
                break;
            case ("refillCard"):
                cardService.refillCard(parseParameterCardId(request), parseParameterAmount(request));
                break;
        }
        return new PageResponse(CARDS_PAGE + "?userId=" + userId, true);
    }

    private CardName parseParameterCardName(HttpServletRequest request) {
        return CardName.valueOf(request.getParameter("cardName"));
    }

    private long parseParameterCardId(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("cardId"));
    }

    private BigDecimal parseParameterAmount(HttpServletRequest request) {
        return new BigDecimal(request.getParameter("amount"));
    }
}
