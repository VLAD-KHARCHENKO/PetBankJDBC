package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import javax.servlet.http.HttpServletRequest;
import static com.project.petbank.view.PageUrlConstants.NOT_FOUND_PAGE;

public class NotFoundCommand implements Command {
    @Override
    public PageResponse execute(HttpServletRequest request) {
        return new PageResponse(NOT_FOUND_PAGE);
    }
}
