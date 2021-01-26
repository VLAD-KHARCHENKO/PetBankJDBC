package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
import com.project.petbank.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


import static com.project.petbank.view.PageUrlConstants.USERS_PAGE;
import static java.util.Objects.isNull;


public class UsersCommand extends UniCommand {
    private UserService userService;
    private static final Logger LOG = Logger.getLogger(UsersCommand.class);

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    public UsersCommand() {

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
            size = 3;
        } else {
            size = Integer.parseInt(sizeStr);
        }
        LOG.info("page="+page+" size="+size);
        request.setAttribute("users", userService.getAllPaginated(page, size));
        LOG.info("Set users");

        return new PageResponse(USERS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}