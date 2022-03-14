package com.springboot.security.custom.basic.spring.security.token.auth.config;

import com.springboot.security.custom.basic.spring.security.token.auth.model.BasicAuthToken;
import com.springboot.security.custom.basic.spring.security.token.auth.model.BasicAuthTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BasicAuthTokenProvider basicAuthTokenProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        BasicAuthToken basicAuthToken = this.getBasicAuthToken(email);
        if (this.checkUsernamePassword(email, password, basicAuthToken)) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            basicAuthToken.getRoles().forEach(r -> authorityList.add(new SimpleGrantedAuthority(r.toString())));
            return new UsernamePasswordAuthenticationToken(email, password, authorityList);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
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
