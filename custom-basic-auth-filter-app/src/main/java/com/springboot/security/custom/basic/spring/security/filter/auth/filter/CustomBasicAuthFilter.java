package com.springboot.security.custom.basic.spring.security.filter.auth.filter;

import com.springboot.security.custom.basic.spring.security.filter.auth.model.BasicAuthToken;
import com.springboot.security.custom.basic.spring.security.filter.auth.model.BasicAuthTokenProvider;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class CustomBasicAuthFilter extends HttpFilter {

    @Autowired
    private BasicAuthTokenProvider basicAuthTokenProvider;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String basicAuthString = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (basicAuthString != null && StringUtils.isNoneBlank(basicAuthString)) {
            String decodedBasicAuth = basicAuthString.substring(6);
            String plainToken = new String(Base64.getDecoder().decode(decodedBasicAuth));
            String username = this.tokenAuthStringHandler(plainToken)[0];
            String password = this.tokenAuthStringHandler(plainToken)[1];
            BasicAuthToken basicAuthToken = this.getBasicAuthToken(username);
            if (this.checkUsernamePassword(username, password, basicAuthToken)) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                basicAuthToken.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.toString())));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private BasicAuthToken getBasicAuthToken(String email) {
        return this.basicAuthTokenProvider.getTokens()
                .stream()
                .filter(t -> this.tokenAuthStringHandler(this.getPlainToken(t.getToken()))[0].equals(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Input email not found!"));
    }

    private boolean checkUsernamePassword(String email, String password, BasicAuthToken basicAuthToken) {
        String plainToken = this.getPlainToken(basicAuthToken.getToken());
        String tokenUsername = this.tokenAuthStringHandler(plainToken)[0];
        String tokenPassword = this.tokenAuthStringHandler(plainToken)[1];
        return tokenUsername.equals(email) && tokenPassword.equals(password);
    }

    private String getPlainToken(String token) {
        return new String(Base64.getDecoder().decode(token.getBytes(StandardCharsets.UTF_8)));
    }

    private String[] tokenAuthStringHandler(String plainToken) {
        return plainToken.split(":");
    }


}
