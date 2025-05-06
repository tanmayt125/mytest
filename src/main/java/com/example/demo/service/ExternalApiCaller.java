package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalApiCaller {

//    @Autowired
//    private SecretsCacheRefresher secretsCacheRefresher;
//
//    @Autowired
//    private AwsSecretsService awsSecretsService;
//
//    @Autowired
//    private WebClient webClient;
//
//    public String callExternalApi(String secretName, String apiUrl) {
//        String secret = awsSecretsService.getSecret(secretName); // get from cache
//
//        try {
//            return makeRestCall(secret, apiUrl);
//        } catch (Exception ex) {
//            System.out.println("Initial REST call failed for " + secretName + ". Attempting cache refresh...");
//            secretsCacheRefresher.refreshSecret(secretName); // refresh cache
//            try {
//                String newSecret = awsSecretsService.getSecret(secretName); // refetched
//                return makeRestCall(newSecret, apiUrl); // retry with new creds
//            } catch (Exception retryEx) {
//                // Log properly or rethrow custom error
//                throw new RuntimeException("CAM-5001: Failed even after refreshing cache - " + retryEx.getMessage(), retryEx);
//            }
//        }
//    }
//
//    private String makeRestCall(String secretValue, String apiUrl) {
//        // Use secret in header/body as per need
//        return webClient.get()
//                .uri(apiUrl)
//                .header("Authorization", "Bearer " + secretValue)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block(); // or use async
//    }
}
