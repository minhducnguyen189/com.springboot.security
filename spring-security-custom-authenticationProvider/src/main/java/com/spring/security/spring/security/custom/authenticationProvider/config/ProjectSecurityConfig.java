package com.spring.security.spring.security.custom.authenticationProvider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/accounts/**").authenticated()
                .antMatchers("/v1/balance").authenticated()
                .antMatchers("/v1/loan").authenticated()
                .antMatchers("/v1/card").authenticated()
                .antMatchers("/v1/contact").permitAll()
                .antMatchers("/v1/notice").permitAll()
                .and().formLogin()
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
