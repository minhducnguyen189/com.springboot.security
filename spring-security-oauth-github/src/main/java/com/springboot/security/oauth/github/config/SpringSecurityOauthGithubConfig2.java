package com.springboot.security.oauth.github.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SpringSecurityOauthGithubConfig2 {

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
        return ClientRegistration.withRegistrationId("github")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientId("c224cc2eb828c3ba811c")
                .clientSecret("7515f35ceb176a13911efa24f85238ff2d8e1d4c")
                .scope("read:user")
                .authorizationUri("https://github.com/login/oauth/authorize")
                .tokenUri("https://github.com/login/oauth/access_token")
                .userInfoUri("https://api.github.com/user")
                .userNameAttributeName("id")
                .clientName("GitHub")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .registrationId("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .build();
    }

}
