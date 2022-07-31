package com.springboot.security.custom.basic.spring.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

public class AuthoritiesLoggingAfterFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthoritiesLoggingAfterFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            LOGGER.info("User with email: " + authentication.getName() + "log in successfully with authorities " + authentication.getAuthorities());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
