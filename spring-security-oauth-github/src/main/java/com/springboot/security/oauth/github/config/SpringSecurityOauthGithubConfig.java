package com.springboot.security.oauth.github.config;

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
                .clientId("c224cc2eb828c3ba811c")
                .clientSecret("7515f35ceb176a13911efa24f85238ff2d8e1d4c")
                .build();
    }

}
