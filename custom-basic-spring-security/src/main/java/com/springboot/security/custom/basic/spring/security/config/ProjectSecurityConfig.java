package com.springboot.security.custom.basic.spring.security.config;

import com.springboot.security.custom.basic.spring.security.constant.SecurityConstant;
import com.springboot.security.custom.basic.spring.security.filter.*;
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
//        http.csrf()
                .ignoringAntMatchers("/v1/user")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().cors()
                .and().addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtTokenGeneratorFilter, BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers("/v1/user").authenticated()
//                .antMatchers("/v1/accounts/**").authenticated()
//                .antMatchers("/v1/balance").authenticated()
//                .antMatchers("/v1/loan").authenticated()
//                .antMatchers("/v1/card").authenticated()
//                .antMatchers("/v1/user").hasAnyAuthority("READ", "WRITE", "DELETE")
//                .antMatchers("/v1/accounts/**").hasAnyAuthority("WRITE", "DELETE")
//                .antMatchers("/v1/balance").hasAuthority("READ")
//                .antMatchers("/v1/loan").hasAuthority("WRITE")
//                .antMatchers("/v1/card").hasAuthority("DELETE")
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
