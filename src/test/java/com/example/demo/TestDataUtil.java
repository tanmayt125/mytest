package com.example.demo;

public class TestDataUtil {
    public static Map<String, String> failureConfig() {
        return Map.of(
                "AA_PORT_STATUS", "The UNI port on your Optus Access Router appears to be inactive...",
                "NTU_PORT_STATUS_UNI", "The UNI port on your Optus NTU appears to be inactive...",
                "STATIC_ROUTES", "No static routes are currently enabled on your device...",
                "BGP_PEER_SUMMARY", "The BGP peer connection could not be established...",
                "UNI-E Status", "The UNI port on your NBN equipment appears to be inactive..."
        );
    }
}
