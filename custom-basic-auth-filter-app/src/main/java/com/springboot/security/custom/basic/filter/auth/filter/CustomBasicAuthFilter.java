package com.springboot.security.custom.basic.filter.auth.filter;

import com.springboot.security.custom.basic.filter.auth.model.BasicAuthToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Component
public class CustomBasicAuthFilter extends HttpFilter {

    @Autowired
    private BasicAuthToken basicAuthToken;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String basicAuthString = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (basicAuthString != null && StringUtils.isNoneBlank(basicAuthString)) {
            String decodedBasicAuth = basicAuthString.substring(6);
            String plainToken = new String(Base64.getDecoder().decode(decodedBasicAuth));
            String username = this.tokenAuthStringHandler(plainToken)[0];
            String password = this.tokenAuthStringHandler(plainToken)[1];
            if (this.checkUsernamePassword(username, password)) {
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(basicAuthToken.getRole().toString()));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkUsernamePassword(String email, String password) {
        String plainToken = new String(Base64.getDecoder().decode(basicAuthToken.getToken().getBytes(StandardCharsets.UTF_8)));
        String tokenUsername = this.tokenAuthStringHandler(plainToken)[0];
        String tokenPassword = this.tokenAuthStringHandler(plainToken)[1];
        return tokenUsername.equals(email) && tokenPassword.equals(password);
    }

    private String[] tokenAuthStringHandler(String plainToken) {
        return plainToken.split(":");
    }


}
