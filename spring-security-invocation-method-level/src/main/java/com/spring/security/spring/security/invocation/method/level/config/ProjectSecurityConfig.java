package com.spring.security.spring.security.invocation.method.level.config;

import com.spring.security.spring.security.invocation.method.level.constant.SecurityConstant;
import com.spring.security.spring.security.invocation.method.level.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthoritiesLoggingAfterFilter authoritiesLoggingAfterFilter;
    @Autowired
    private AuthoritiesLoggingAtFilter authoritiesLoggingAtFilter;
    @Autowired
    private RequestValidationBeforeFilter requestValidationBeforeFilter;
    @Autowired
    private JwtTokenGeneratorFilter jwtTokenGeneratorFilter;
    @Autowired
    private JwtTokenValidatorFilter jwtTokenValidatorFilter;

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
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf()
                .ignoringAntMatchers("/v1/user")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().cors()
                .and().addFilterBefore(requestValidationBeforeFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(authoritiesLoggingAfterFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtTokenGeneratorFilter, BasicAuthenticationFilter.class)
                .addFilterAt(authoritiesLoggingAtFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
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
        corsConfiguration.setExposedHeaders(Collections.singletonList(SecurityConstant.AUTHORIZATION_HEADER));
        corsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
