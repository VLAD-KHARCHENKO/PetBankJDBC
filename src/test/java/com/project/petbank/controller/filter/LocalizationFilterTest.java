package com.project.petbank.controller.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LocalizationFilterTest {
    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";
    public static final String LOCALE_VALUE = "locale_value";
    public static final String BUNDLE_VALUE = "bundle_value";

    @InjectMocks
    private LocalizationFilter instance;

    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        when(filterConfig.getInitParameter(LOCALE)).thenReturn(LOCALE_VALUE);
        when(filterConfig.getInitParameter(BUNDLE)).thenReturn(BUNDLE_VALUE);
        when(request.getSession()).thenReturn(session);

    }

    @Test
    public void shouldPopulateLocaleFromConfig() throws ServletException {
        instance.init(filterConfig);

        assertThat(instance.getDefaultLocale()).isEqualTo(LOCALE_VALUE);
    }

    @Test
    public void shouldPopulateBundleFromConfig() {
        instance.init(filterConfig);

        assertThat(instance.getDefaultBundle()).isEqualTo(BUNDLE_VALUE);
    }

    @Test
    public void shouldPopulateLocaleToSession() throws ServletException, IOException {
        instance.init(filterConfig);
        instance.doFilter(request, response, filterChain);

        verify(session).setAttribute(LOCALE, LOCALE_VALUE);
    }

    @Test
    public void shouldNotPopulateLocaleToSessionWhenExistAlready() throws ServletException, IOException {
        when(session.getAttribute(LOCALE)).thenReturn(LOCALE_VALUE);
        instance.init(filterConfig);
        instance.doFilter(request, response, filterChain);

        verify(session, never()).setAttribute(eq(LOCALE), anyString());
    }

    @Test
    public void shouldPopulateBundleToSession() throws ServletException, IOException {
        instance.init(filterConfig);
        instance.doFilter(request, response, filterChain);

        verify(session).setAttribute(BUNDLE, BUNDLE_VALUE);
    }

    @Test
    public void shouldNotPopulateBundleToSessionWhenExistAlready() throws ServletException, IOException {
        when(session.getAttribute(BUNDLE)).thenReturn(BUNDLE_VALUE);
        instance.init(filterConfig);
        instance.doFilter(request, response, filterChain);

        verify(session, never()).setAttribute(BUNDLE, BUNDLE_VALUE);
    }
}
