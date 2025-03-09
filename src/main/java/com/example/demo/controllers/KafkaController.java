package com.example.demo.controllers;

import com.example.demo.service.KafkaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody String message) {
        boolean result = this.kafkaService.sendMessage("producer-out-0", message);

        if (result) {
            return "Message sent successfully";
        } else {
            return "Error trying to send message";
        }
    }
}
