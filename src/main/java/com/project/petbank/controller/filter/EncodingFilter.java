package com.project.petbank.controller.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class EncodingFilter implements Filter {
    private static final Logger LOG = Logger.getLogger("EncodingFilter");
    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String DEFAULT_CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String REQUEST_ENCODING = "requestEncoding";
    private String defaultEncoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        defaultEncoding = config.getInitParameter(REQUEST_ENCODING);
        if (defaultEncoding == null) {
            defaultEncoding = ENCODING_UTF_8;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.info("Execution EncodingFilter.");
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(defaultEncoding);
        }
        response.setContentType(DEFAULT_CONTENT_TYPE);
        response.setCharacterEncoding(defaultEncoding);
        chain.doFilter(request, response);
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }
}
