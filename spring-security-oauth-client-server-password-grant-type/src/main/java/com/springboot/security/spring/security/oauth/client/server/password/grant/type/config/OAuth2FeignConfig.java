package com.springboot.security.spring.security.oauth.client.server.password.grant.type.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class OAuth2FeignConfig {

    public static final String CLIENT_REGISTRATION_ID = "keycloak";

    @Value("${spring.security.oauth2.client.registration.keycloak.username}")
    private String username;

    @Value("${spring.security.oauth2.client.registration.keycloak.password}")
    private String password;

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public OAuth2FeignConfig(ClientRegistrationRepository clientRegistrationRepository,
                             OAuth2AuthorizedClientService authorizedClientService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        OAuthClientCredentialsFeignManager clientCredentialsFeignManager =
                new OAuthClientCredentialsFeignManager(authorizedClientManager(), clientRegistrationRepository);
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, clientCredentialsFeignManager.getAccessToken(CLIENT_REGISTRATION_ID));
        };
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager() {
        OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .password()
                .refreshToken()
                .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);


        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        authorizedClientManager.setContextAttributesMapper(oAuth2AuthorizeRequest -> {
            Map<String, Object> map = new HashMap<>();
            map.put(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME, username);
            map.put(OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME, password);
            return map;
        });
        return authorizedClientManager;
    }

}
