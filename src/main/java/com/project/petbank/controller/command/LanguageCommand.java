package com.restaurant.controller.command.admin;

import com.restaurant.controller.command.Command;
import com.restaurant.controller.data.PageResponse;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LanguageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);
    public static final String LOCALE = "locale";

    @Override
    public PageResponse execute(HttpServletRequest request) {
        String locale = request.getParameter(LOCALE);
        String originUrl = request.getHeader("referer");
        request.getSession().setAttribute(LOCALE, locale);
        LOG.info("locale= " + locale);

        return new PageResponse(originUrl, true);
    }
}
