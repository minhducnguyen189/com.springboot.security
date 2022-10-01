package com.spring.security.spring.security.jwt.filter;

import com.spring.security.spring.security.jwt.constant.SecurityConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Value("${security.token.secret}")
    private String secret;
    @Value("${security.token.timeout}")
    private Long timeout;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Date now = new Date();
            String jwt = Jwts.builder()
                    .setIssuer(SecurityConstant.ISSUER)
                    .setSubject(SecurityConstant.SUBJECT)
                    .claim(SecurityConstant.CLAIM_USERNAME, authentication.getName())
                    .claim(SecurityConstant.CLAIM_AUTHORITIES, this.getAuthorityString(authentication.getAuthorities()))
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + timeout))
                    .signWith(key)
                    .compact();
            response.setHeader(SecurityConstant.AUTHORIZATION_HEADER, jwt);
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthorityString(Collection<? extends GrantedAuthority> grantedAuthorities) {
        Set<String> authorities = new HashSet<>();
        grantedAuthorities.forEach(g -> authorities.add(g.getAuthority()));
        return String.join(",", authorities);
    }
}
