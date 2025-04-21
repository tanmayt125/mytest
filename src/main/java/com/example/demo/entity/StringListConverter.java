package com.example.demo.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Converter(autoApply = true)
//public class StringListConverter implements AttributeConverter<List<String>, String> {
//
//    @Override
//    public String convertToDatabaseColumn(List<String> attribute) {
//        if (attribute == null || attribute.isEmpty()) {
//            return "{}"; // PostgreSQL empty array syntax
//        }
//        return "{" + String.join(",", attribute) + "}";
//    }
//
//    @Override
//    public List<String> convertToEntityAttribute(String dbData) {
//        if (dbData == null || dbData.equals("{}")) {
//            return List.of(); // Return empty list
//        }
//        return Arrays.stream(dbData.replaceAll("[{}]", "").split(","))
//                .map(String::trim)
//                .collect(Collectors.toList());
//    }
//}
