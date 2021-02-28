package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;

import javax.servlet.http.HttpServletRequest;

public abstract class UniCommand implements Command {

    @Override
    public PageResponse execute(HttpServletRequest request) {
        String type = request.getMethod();

        return "GET".equals(type)
                ? performGet(request)
                : performPost(request);
    }

    protected abstract PageResponse performGet(HttpServletRequest request);

    protected abstract PageResponse performPost(HttpServletRequest request);
}
