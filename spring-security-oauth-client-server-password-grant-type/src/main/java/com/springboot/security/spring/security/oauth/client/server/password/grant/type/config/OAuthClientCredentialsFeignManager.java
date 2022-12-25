package com.springboot.security.spring.security.oauth.client.server.password.grant.type.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import static java.util.Objects.isNull;

@Slf4j
public class OAuthClientCredentialsFeignManager {

    private static final Authentication ANONYMOUS_USER_AUTHENTICATION =
            new AnonymousAuthenticationToken("key", "anonymous", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
    private final ClientRegistrationRepository clientRegistrationRepository;


    public OAuthClientCredentialsFeignManager(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager,
                                              ClientRegistrationRepository clientRegistrationRepository) {
        this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    public String getAccessToken(String clientRegistrationId) {
        try {
            ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
            OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId(clientRegistration.getRegistrationId())
                    .principal(ANONYMOUS_USER_AUTHENTICATION)
                    .build();
            OAuth2AuthorizedClient client = oAuth2AuthorizedClientManager.authorize(oAuth2AuthorizeRequest);
            if (isNull(client)) {
                throw new IllegalStateException("client credentials flow on " + clientRegistration.getRegistrationId() + " failed, client is null");
            }
            return "Bearer " + client.getAccessToken().getTokenValue();
        } catch (Exception exp) {
            log.error("client credentials error " + exp.getMessage());
            throw new IllegalArgumentException("client credentials error " + exp.getMessage(), exp);
        }
    }

}
