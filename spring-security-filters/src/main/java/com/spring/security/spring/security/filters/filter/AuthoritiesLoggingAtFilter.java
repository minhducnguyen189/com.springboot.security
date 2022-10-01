package com.spring.security.spring.security.filters.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class AuthoritiesLoggingAtFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthoritiesLoggingAtFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Authentication Validation is in progress!");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
