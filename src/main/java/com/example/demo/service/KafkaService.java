package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final StreamBridge streamBridge;

    @Autowired
    public KafkaService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public boolean sendMessage(String topic, String message) {
        return this.streamBridge.send(topic, message);
    }
}
