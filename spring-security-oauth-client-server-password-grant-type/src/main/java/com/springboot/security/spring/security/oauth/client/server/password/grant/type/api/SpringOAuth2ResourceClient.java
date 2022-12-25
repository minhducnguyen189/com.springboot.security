package com.springboot.security.spring.security.oauth.client.server.password.grant.type.api;

import com.springboot.security.spring.security.oauth.client.server.password.grant.type.config.OAuth2FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "springOAuth2ResourceClient", url = "${spring.security.oauth2.url}", configuration = {OAuth2FeignConfig.class})
public interface SpringOAuth2ResourceClient {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/card")
    String getCardDetail();

}
