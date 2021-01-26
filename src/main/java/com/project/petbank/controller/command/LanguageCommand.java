package com.project.petbank.controller.command;


import com.project.petbank.controller.data.PageResponse;
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
