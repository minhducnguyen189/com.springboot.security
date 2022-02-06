package com.springboot.security.custom.basic.token.auth.config;

import com.springboot.security.custom.basic.token.auth.model.BasicAuthToken;
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
    private BasicAuthToken basicAuthToken;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (this.checkUsernamePassword(email, password)) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(basicAuthToken.getRole().toString()));
            return new UsernamePasswordAuthenticationToken(email, password, authorityList);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
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
