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
        String sort = request.getParameter("sort");
        String currentDirection = request.getParameter("direction");
        if(currentDirection==null) {
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
        LOG.info("page="+page+" size="+size + "sort= " +sort+ "currentDirection"+ currentDirection);
        request.setAttribute("users", userService.getAllPaginated(page, size, sort, currentDirection));
        LOG.info("Set users");
        request.setAttribute("currentPage", page);
        request.setAttribute("usersPages", (userService.getAll().size() / size));
        request.setAttribute("currentDirection",currentDirection);
        request.setAttribute("direction", currentDirection.equals("asc") ?"desc":"asc");
        request.setAttribute("sort", sort);

        return new PageResponse(USERS_PAGE);
    }

    @Override
    protected PageResponse performPost(HttpServletRequest request) {

        return null;
    }
}