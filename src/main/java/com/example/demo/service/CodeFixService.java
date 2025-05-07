package com.example.demo.service;

public class CodeFixService {
}


package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    // Getters
    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }
}



.bodyValue("grant_type=client_credentials")
                .retrieve()
                .bodyToMono(OAuthTokenResponse.class)
                .timeout(Duration.ofSeconds(timeoutSeconds))
        .map(OAuthTokenResponse::getAccessToken)
                .block();