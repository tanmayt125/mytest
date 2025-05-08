package com.example.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.Map;

@Service
public class AwsSecretsService {

    private final SecretsManagerClient secretsManagerClient;

    public AwsSecretsService() {
        this.secretsManagerClient = SecretsManagerClient.builder()
                .region(Region.AP_SOUTH_1) // 🔥 your AWS region
                .build();
    }

    @Cacheable(value = "secretsCache")
    public String getSecret(String secretName) {
        System.out.println("Fetching fresh secret from AWS Secrets Manager...");
        try {
            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);

            return response.secretString();
        } catch (Exception ex) {
            throw new RuntimeException("CAM-5001: Failed to fetch secret: " + ex.getMessage());
        }
    }


    @Cacheable(value = "secretsCache", key = "#secretName")
    public Map<String, String> getSecretAsMap(String secretName) {
        System.out.println("Fetching fresh secret from AWS Secrets Manager...");
        try {
            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);
            String secretString = response.secretString();

            // Parse JSON string to Map
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(secretString, new TypeReference<Map<String, String>>() {});
        } catch (Exception ex) {
            throw new RuntimeException("CAM-5001: Failed to fetch secret: " + ex.getMessage());
        }
    }

}
