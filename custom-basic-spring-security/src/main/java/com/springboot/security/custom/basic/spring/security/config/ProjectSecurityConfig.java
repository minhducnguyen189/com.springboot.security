package com.springboot.security.custom.basic.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
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
        http.cors()
                .and().authorizeRequests()
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
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("12345").authorities("admin")
//                .and().withUser("user").password("12345").authorities("read")
//                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user1 = User.withUsername("admin").password("12345").authorities("admin").build();
//        UserDetails user2 = User.withUsername("user").password("12345").authorities("admin").build();
//        userDetailsManager.createUser(user1);
//        userDetailsManager.createUser(user2);
//        auth.userDetailsService(userDetailsManager);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
