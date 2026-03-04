package com.example.demo;

import java.util.*;

public class AddressParser {

    static class AddressInput {

        String formattedAddress;
        String streetNr;
        String streetName;
        String streetType;

        AddressInput(String formattedAddress,
                     String streetNr,
                     String streetName,
                     String streetType) {

            this.formattedAddress = formattedAddress;
            this.streetNr = streetNr;
            this.streetName = streetName;
            this.streetType = streetType;
        }
    }

    public static void main(String[] args) {

        List<AddressInput> inputs = List.of(

                new AddressInput(
                        "UNIT 2, 44 HYLAND STREET, WARRNAMBOOL, VIC, 3280",
                        "44","HYLAND","STREET"),

                new AddressInput(
                        "SHOP T70, 33-45 HUTCHINSON STREET, LILYDALE, VIC, 3140",
                        "33-45","HUTCHINSON","STREET"),

                new AddressInput(
                        "WOOLSTORE 61, LOT X, 5A CANAL ROAD, ST PETERS, NSW, 2044",
                        "5A","CANAL","ROAD"),

                new AddressInput(
                        "THE GROSVENOR, UNIT 1404, 12 EDWARD STREET, BRISBANE CITY, QLD, 4000",
                        "12","EDWARD","STREET"),

                new AddressInput(
                        "ROOM LIFT2, 75 EAST STREET, LIDCOMBE, NSW, 2141",
                        "75","EAST","STREET"),

                new AddressInput(
                        "KATHLEENS PARK, UNIT 7, 17 PINKERTON CIRCUIT, KAMBAH, ACT, 2902",
                        "17","PINKERTON","CIRCUIT"));


        int i=1;

        for(AddressInput input:inputs){

            System.out.println("\n=========================");
            System.out.println("Example "+i++);
            System.out.println("Input : "+input.formattedAddress);

            Map<String,String> result = transform(input);

            result.forEach((k,v)->System.out.println(k+" : "+v));
        }
    }

    public static Map<String,String> transform(AddressInput input){

        String v1="";
        String v2="";
        String v3="";
        String v4="";
        String v5="";
        String v6="";
        String v7="";
        String v8="";

        // Use structured fields first
        if(input.streetNr!=null)
            v3=input.streetNr;

        if(input.streetName!=null)
            v5=input.streetName;

        if(input.streetType!=null)
            v7=input.streetType;

        String[] parts=input.formattedAddress.split(",");

        // remove suburb state postcode
        List<String> core=new ArrayList<>();

        for(int i=0;i<parts.length-3;i++)
            core.add(parts[i].trim());

        for(String part:core){

            String[] tokens=part.split(" ");

            if(tokens[0].equals("UNIT")
                    || tokens[0].equals("SHOP")
                    || tokens[0].equals("LOT")){

                v1=tokens[0];
                if(tokens.length>1)
                    v2=tokens[1];
            }
            else if(!part.contains(v3)){ // building/company name

                if(!v8.isEmpty())
                    v8+=" ";

                v8+=part;
            }
        }

        String transformed=
                String.join("~",
                        v1,
                        v2,
                        v3,
                        v4,
                        v5,
                        v6,
                        v7,
                        v8);

        System.out.println("Transformed V1-V8 : "+transformed);

        return splitStreet(transformed);
    }

    static Map<String,String> splitStreet(String str){

        Map<String,String> map=new LinkedHashMap<>();

        map.put("Street1",slice(str,0,60));
        map.put("Street2",slice(str,60,120));
        map.put("Street3",slice(str,120,180));
        map.put("Street4",slice(str,180,240));

        return map;
    }

    static String slice(String s,int start,int end){

        if(start>=s.length())
            return "";

        return s.substring(start,Math.min(end,s.length()));
    }
}