package com.springboot.security.custom.basic.spring.security.token.auth.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BasicAuthTokenProvider {

    private List<BasicAuthToken> tokens;

}
