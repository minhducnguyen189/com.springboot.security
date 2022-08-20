package com.springboot.security.custom.basic.spring.security.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Component
public class RequestValidationBeforeFilter implements Filter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_SCHEMA_BASIC = "Basic";
    private static final String STRICT_EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.startsWithIgnoreCase(authorizationHeader, AUTHENTICATION_SCHEMA_BASIC)) {
            byte[] base64Token = authorizationHeader.substring(6).getBytes(StandardCharsets.UTF_8);
            String decodedToken = new String(Base64Utils.decode(base64Token));
            String email = decodedToken.substring(0, decodedToken.indexOf(":"));
            if (!this.patternMatches(email)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean patternMatches(String emailAddress) {
        return Pattern.compile(STRICT_EMAIL_PATTERN)
                .matcher(emailAddress)
                .matches();
    }
}
