package com.example.demo.kafkaCaching;

@Component
public class KafkaMessageWorker {

    @Autowired
    private KafkaConfigurationService kafkaConfigService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @JobWorker(type = "publish-kafka")
    public void publishKafkaMessage(JobClient client, ActivatedJob job) {

        try {
            KafkaConfig cfg = kafkaConfigService.getKafkaConfiguration();

            String topic = cfg.getTopic();
            String message = job.getVariablesAsMap().get("message").toString();

            kafkaTemplate.send(topic, message);

            client.newCompleteCommand(job.getKey()).send().join();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}

