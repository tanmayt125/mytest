package com.example.demo.kafkaCaching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class OAuthTokenService {

    @Autowired
    private KafkaConfigurationService kafkaConfigService;

    @Autowired
    private AwsSecretsService awsSecretsService;

    @Cacheable(value = "oauthTokenCache", key = "'oauthToken'")
    public String getToken() {

        System.out.println("FETCHING NEW TOKEN (because cache expired)...");

        KafkaConfig kafkaConfig = kafkaConfigService.getKafkaConfiguration();
        Map<String, String> secrets = awsSecretsService.getSecret();

        String clientId = secrets.get("sdsyncKafkaClientId");
        String clientSecret = secrets.get("sdsyncKafkaClientSecret");
        String tokenUrl = kafkaConfig.getAdditionalProperties().get("sasl.oauthbearer.token.endpoint.url");

        // Most OAuth servers expect client_credentials
        String form = "grant_type=client_credentials" +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret;

        return WebClient.create()
                .post()
                .uri(tokenUrl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue(form)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

