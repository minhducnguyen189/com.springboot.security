package com.springboot.security.encode.decode.app.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Service
public class EncodeDecodeService {

    public String encodeBase64(String data) {
        return Base64Utils.encodeToString(data.getBytes());
    }

    public String decodeBase64(String data) {
        return new String(Base64Utils.decode(data.getBytes()));
    }

    public String encodeUrl(String urlString) {
        return UriUtils.encode(urlString, StandardCharsets.UTF_8);
    }

    public String decodeUrl(String urlString) {
        return UriUtils.decode(urlString, StandardCharsets.UTF_8);
    }

}
