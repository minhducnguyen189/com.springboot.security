package com.springboot.security.hash.app.model;

public class MatchDataRequest {

    private String rawData;
    private String hashedData;

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getHashedData() {
        return hashedData;
    }

    public void setHashedData(String hashedData) {
        this.hashedData = hashedData;
    }
}
