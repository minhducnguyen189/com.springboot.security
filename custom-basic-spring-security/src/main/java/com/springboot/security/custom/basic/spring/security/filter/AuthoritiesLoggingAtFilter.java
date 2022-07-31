package com.springboot.security.custom.basic.spring.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class AuthoritiesLoggingAtFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthoritiesLoggingAtFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Authentication Validation is in progress!");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
