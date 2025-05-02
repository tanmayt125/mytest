package com.example.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Service
public class OAuthTokenService {

    private final WebClient webClient;

    public OAuthTokenService() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String fetchToken(String tokenUrl, String clientId, String clientSecret, int timeoutSeconds) {
        return webClient.post()
                .uri(tokenUrl)
                .headers(headers -> {
                    headers.setBasicAuth(clientId, clientSecret);
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                })
                .bodyValue("grant_type=client_credentials")
                .retrieve()
                .bodyToMono(String.class) // or map to a class if desired
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .block();
    }
}
