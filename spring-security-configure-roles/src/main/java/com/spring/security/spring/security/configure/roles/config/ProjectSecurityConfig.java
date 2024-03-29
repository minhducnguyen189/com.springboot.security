package com.spring.security.spring.security.configure.roles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * contact: Not Secure
     * notice: Not Secure
     * balance: Secure
     * Card: Secure
     * Loan: Secure
     * Account: Secure
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers("/v1/user")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().cors()
                .and().authorizeRequests()
                .antMatchers("/v1/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/v1/accounts/**").hasRole("USER")
                .antMatchers("/v1/balance").hasRole("USER")
                .antMatchers("/v1/loan").hasRole("ADMIN")
                .antMatchers("/v1/card").hasRole("ADMIN")
                .antMatchers("/v1/contact").permitAll()
                .antMatchers("/v1/notice").permitAll()
                .and().formLogin()
                .and().httpBasic();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
