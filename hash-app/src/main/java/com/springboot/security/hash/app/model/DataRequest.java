package com.springboot.security.hash.app.model;

import org.springframework.lang.NonNull;

public class DataRequest {

    private String data;

    @NonNull
    public String getData() {
        return data;
    }

    public void setData(@NonNull String data) {
        this.data = data;
    }

}
