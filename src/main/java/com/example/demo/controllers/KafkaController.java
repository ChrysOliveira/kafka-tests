package com.example.demo.controllers;

import com.example.demo.domain.MessageDomain;
import com.example.demo.service.KafkaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> sendMessage(@RequestBody @Valid MessageDomain messageDomain) {
        boolean result = this.kafkaService.sendMessageToKafka("producer-out-0", messageDomain);

        if (result) {
            return ResponseEntity.ok("Message sent successfully");
        } else {
            return ResponseEntity.badRequest().body("Error trying to send message");
        }
    }
}
