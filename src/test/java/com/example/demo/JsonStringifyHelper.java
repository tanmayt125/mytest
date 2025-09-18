package com.example.demo;

public class JsonStringifyHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> stringifyNested(Map<String, Object> original) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : original.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map || value instanceof List) {
                try {
                    result.put(entry.getKey(), mapper.writeValueAsString(value));
                } catch (Exception e) {
                    throw new RuntimeException("Error stringifying field: " + entry.getKey(), e);
                }
            } else {
                result.put(entry.getKey(), value);
            }
        }
        return result;
    }
}

