package com.example.demo.service;

import com.example.demo.domain.MessageDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.net.SocketTimeoutException;

@Service
public class KafkaService {

    private final StreamBridge streamBridge;
    private final RestClient restClient;

    @Autowired
    public KafkaService(StreamBridge streamBridge, RestClient restClient) {
        this.streamBridge = streamBridge;
        this.restClient = restClient;
    }

    public boolean sendMessageToKafka(String topic, MessageDomain message) {
        Message<MessageDomain> kafkaMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.KEY, message.message())
                .build();

        return this.streamBridge.send(topic, kafkaMessage);
    }

    public void sendMessageRest(MessageDomain messageDomain) {
        try {
            String response = this.restClient
                    .post()
                    .uri("https://webhook.site/32860a2c-d3f1-4813-b409-d4a007353fcd")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(messageDomain)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        System.out.println("RECEIVED HTTP STATUS CODE" + res.getStatusCode());
                    })
                    .body(String.class);

            System.out.println("Success: " + response);

        } catch (RestClientResponseException e) {
            System.err.println("HTTP Error: " + e.getStatusCode() + " - " + e.getMessage());

        } catch (ResourceAccessException e) {
            System.err.println("ResourceAccessException: " + e.getMessage());
        }
    }
}