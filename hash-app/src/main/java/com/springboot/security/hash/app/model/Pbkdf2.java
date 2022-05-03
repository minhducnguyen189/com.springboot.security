package com.springboot.security.hash.app.model;


public class Pbkdf2 {

    private String salt;
    private Integer iterations;
    private Integer hashWidth;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public Integer getHashWidth() {
        return hashWidth;
    }

    public void setHashWidth(Integer hashWidth) {
        this.hashWidth = hashWidth;
    }
}
