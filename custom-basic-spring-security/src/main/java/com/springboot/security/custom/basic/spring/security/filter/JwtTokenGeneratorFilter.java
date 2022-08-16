package com.springboot.security.custom.basic.spring.security.filter;

import com.springboot.security.custom.basic.spring.security.constant.SecurityConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstant.SECRET.getBytes(StandardCharsets.UTF_8));
            Date now = new Date();
            String jwt = Jwts.builder()
                    .setIssuer(authentication.getName())
                    .setSubject("JWT")
                    .claim("username", authentication.getName())
                    .claim("authorities", this.getAuthorityString(authentication.getAuthorities()))
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + 30000))
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization", jwt);
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthorityString(Collection<? extends GrantedAuthority> grantedAuthorities) {
        Set<String> authorities = new HashSet<>();
        grantedAuthorities.forEach(g -> authorities.add(g.getAuthority()));
        return String.join(",", authorities);
    }
}
