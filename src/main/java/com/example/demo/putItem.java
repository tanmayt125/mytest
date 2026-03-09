public PutItemResponse putDynamoDBItem(String ttl, String key, String value) {

    LOG.info(">>>dynamoDB>>>putDynamoDBItem>>Key -> {}, Value -> {}", key, value);

    long createDate = System.currentTimeMillis() / 1000L;
    long expireDate;

    if (Strings.isEmpty(ttl)) {

        LOG.info(">>>dynamoDB>>>putDynamoDBItem->default ttl");

        expireDate = createDate + Long.parseLong((String) dynamoDetails.get("defaultTtl"));

    } else {

        expireDate = createDate + Long.parseLong(ttl);
    }

    LOG.info(">>>dynamoDB>>>Expiry Date: {}", expireDate);

    HashMap<String, AttributeValue> keyToPut = new HashMap<>();

    keyToPut.put("pk", AttributeValue.builder().s(key).build());
    keyToPut.put("value", AttributeValue.builder().s(value).build());
    keyToPut.put("ttl", AttributeValue.builder().n(String.valueOf(expireDate)).build());

    String tableName = (String) dynamoDetails.get("dynamo.db.table.name");

    LOG.info(">>>putDynamoDBItem>>, Table Name: {}", tableName);

    try {

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(keyToPut)
                .build();

        LOG.info("After PutItemRequest");

        PutItemResponse response = this.getDynamoDbClient().putItem(request);

        LOG.info("PutItem operation with TTL successful. Request id is -> {}",
                response.responseMetadata().requestId());

        return response;

    } catch (DynamoDbException e) {

        LOG.error("DynamoDbException putDynamoDBItem -> {}", e.getMessage());
        LOG.error("DynamoDbException putDynamoDBItem Trace -> {}", e);
        throw new RuntimeException(e.getMessage());

    } catch (Exception e) {

        LOG.error("Exception for putDynamoDBItem -> {}", e.getMessage());
        LOG.error("Exception for putDynamoDBItem Trace -> {}", e);
        throw new RuntimeException(e.getMessage());
    }
}

long ttl = Instant.now()
        .plus(30, ChronoUnit.DAYS)
        .getEpochSecond();