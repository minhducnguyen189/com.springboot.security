package com.springboot.sprint.security.oauth.client.credentials.grant.type.config;

import com.springboot.sprint.security.oauth.client.credentials.grant.type.constant.SecurityConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().configurationSource(this.corsConfigurationSource())
                .and().csrf().ignoringAntMatchers("/v1/user")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().authorizeRequests()
                .antMatchers("/v1/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/v1/accounts/**").hasRole("USER")
                .antMatchers("/v1/balance").hasRole("USER")
                .antMatchers("/v1/loan").hasRole("ADMIN")
                .antMatchers("/v1/card").hasRole("ADMIN")
                .antMatchers("/v1/contact").permitAll()
                .antMatchers("/v1/notice").permitAll()
                .and().oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
        return http.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(Collections.singletonList(SecurityConstant.AUTHORIZATION_HEADER));
        corsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
