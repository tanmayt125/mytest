package com.example.demo.service;

import com.example.demo.dao.OAuthTokenResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthTokenService {

    private final WebClient webClient;

    public AuthTokenService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "authTokenCache1")
    public String getAuthTokenForUrl1() {
        System.out.println("Fetching fresh auth token for URL1...");
        try {
            String token = webClient.post()
                    .uri("https://your-oauth-provider-url1.com/oauth/token")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .bodyValue("grant_type=client_credentials&client_id=your-client-id-1&client_secret=your-client-secret-1")
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .map(errorBody -> new RuntimeException("CAM-5001: " + errorBody))
                    )
                    .bodyToMono(OAuthTokenResponse.class)
                    .map(OAuthTokenResponse::getAccessToken)
                    .block();

            return token;
        } catch (Exception ex) {
            // Exception handling: return CAM-5001 + original error message
            throw new RuntimeException("CAM-5001: " + ex.getMessage());
        }
    }

    @Cacheable(value = "authTokenCache2")
    public String getAuthTokenForUrl2() {
        System.out.println("Fetching fresh auth token for URL2...");
        try {
            String token = webClient.post()
                    .uri("https://your-oauth-provider-url2.com/oauth/token")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .bodyValue("grant_type=client_credentials&client_id=your-client-id-2&client_secret=your-client-secret-2")
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .map(errorBody -> new RuntimeException("CAM-5001: " + errorBody))
                    )
                    .bodyToMono(OAuthTokenResponse.class)
                    .map(OAuthTokenResponse::getAccessToken)
                    .block();

            return token;
        } catch (Exception ex) {
            // Exception handling: return CAM-5001 + original error message
            throw new RuntimeException("CAM-5001: " + ex.getMessage());
        }
    }
}
