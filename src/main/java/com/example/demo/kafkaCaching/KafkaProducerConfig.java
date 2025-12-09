package com.example.demo.kafkaCaching;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaConfigurationService kafkaConfigService;

    @Autowired
    private AwsSecretsService awsSecretsService;

    @Autowired
    private OAuthTokenService tokenService;


    @Bean
    public ProducerFactory<String, String> producerFactory() {

        KafkaConfig cfg = kafkaConfigService.getKafkaConfiguration();

        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, cfg.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Load additional properties from configmap
        Map<String, String> additional = cfg.getAdditionalProperties();
        props.putAll(additional);

        // 1) Fetch AWS Secrets
        Map<String, String> awsSecrets = awsSecretsService.getSecret();
        String clientId = awsSecrets.get("sdsyncKafkaClientId");
        String clientSecret = awsSecrets.get("sdsyncKafkaClientSecret");

        // 2) Fetch OAuth token (cached)
        String token = tokenService.getToken();

        // 3) Build JAAS config
        String jaas = additional.get("sasl.jaas.config");

        jaas = jaas.replace("dynamicUser", clientId)
                .replace("dynamicPassword", clientSecret);

        // ADD our token into JAAS
        // Confluent OAuth supports oauthToken="<token>"
        jaas = jaas + " oauthToken=\"" + token + "\";";

        props.put("sasl.jaas.config", jaas);

        return new DefaultKafkaProducerFactory<>(props);
    }


    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

