package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class ErrorExtractor {
    public static void main(String[] args) {
        String jsonString = "{\n" +
                "  \"id\": \"9171303055313961786\",\n" +
                "  \"name\": \"SD L3 Service CFS Instance #0122753630\",\n" +
                "  \"href\": \"/inventoryManagement/18.5/service/9171303055313961786\",\n" +
                "  \"state\": \"active\",\n" +
                "  \"category\": \"Uncategorized\",\n" +
                "  \"serviceCharacteristic\": [\n" +
                "    {\n" +
                "      \"valueType\": \"String\",\n" +
                "      \"name\": \"bandwidth\",\n" +
                "      \"value\": \"500\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"valueType\": \"String\",\n" +
                "      \"name\": \"access_external_service_id\",\n" +
                "      \"value\": \"9171303056313961786\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"valueType\": \"String\",\n" +
                "      \"name\": \"gold_rt\",\n" +
                "      \"value\": \"20\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"relatedParty\": [\n" +
                "    {\n" +
                "      \"role\": \"customer\",\n" +
                "      \"name\": \"OPLXQBRQ\",\n" +
                "      \"id\": \"NRZGA\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonString);
            JsonNode characteristics = rootNode.get("serviceCharacteristic");

            String accessId = null;
            for (JsonNode node : characteristics) {
                String name = node.get("name").asText();
                if ("access_external_service_id".equals(name)) {
                    accessId = node.get("value").asText();
                    break;
                }
            }

            if (accessId != null) {
                System.out.println("access_external_service_id: " + accessId);
            } else {
                System.out.println("access_external_service_id not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
