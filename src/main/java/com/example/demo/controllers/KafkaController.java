package com.example.demo.controllers;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final StreamBridge streamBridge;

    public KafkaController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody String message) {
        streamBridge.send("producer-out-0", message);
        return "Message sent successfully";
    }
}
