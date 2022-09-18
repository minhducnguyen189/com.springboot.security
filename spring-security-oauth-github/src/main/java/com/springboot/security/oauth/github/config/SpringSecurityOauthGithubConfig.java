package com.springboot.security.oauth.github.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityOauthGithubConfig {

    @Value("${github.oauth2.client.id}")
    private String clientId;

    @Value("${github.oauth2.client.secret}")
    private String clientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests().anyRequest().authenticated().and().oauth2Login().and().build();
    }

    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration clientRegistration = this.clientRegistration();
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GITHUB
                .getBuilder("github")
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .build();
    }

}
