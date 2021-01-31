package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.service.AccountService;
import com.project.petbank.service.CardService;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.ACCOUNTS_PAGE;
import static com.project.petbank.view.PageUrlConstants.CARDS_PAGE;
import static java.util.Objects.isNull;


public class AccountCommand extends UniCommand {
    private AccountService accountService;
    private static final Logger LOG = Logger.getLogger(AccountCommand.class);

    public AccountCommand(AccountService accountService) {
        this.accountService = accountService;
    }



    public AccountCommand() {
    }

    @Override
    protected PageResponse performGet(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        Integer size;
        Integer page;
        if (isNull(pageStr)) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        if (isNull(sizeStr)) {
            size = 30;
        } else {
            size = Integer.parseInt(sizeStr);
        }
        LOG.info("page="+page+" size="+size);
        request.setAttribute("accounts", accountService.getAllPaginated(page, size));
        LOG.info("Set account");

        return new PageResponse(ACCOUNTS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}