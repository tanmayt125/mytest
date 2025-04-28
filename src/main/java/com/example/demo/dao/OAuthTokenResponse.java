package com.example.demo.dao;

public class OAuthTokenResponse {

    private String access_token;

    // Default Constructor
    public OAuthTokenResponse() {
    }

    // Getter
    public String getAccessToken() {
        return access_token;
    }

    // Setter
    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }
}
