package com.project.petbank.controller.command;



import com.project.petbank.controller.data.PageResponse;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    PageResponse execute(HttpServletRequest request);
}
