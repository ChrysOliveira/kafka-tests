package com.example.demo.listeners;

import com.example.demo.domain.MessageDomain;
import com.example.demo.service.KafkaService;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class KafkaListener {

    private final KafkaService kafkaService;

    public KafkaListener(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @Bean
    public Consumer<Message<MessageDomain>> consume(){
        return msg -> kafkaService.sendMessageRest(msg.getPayload());
    }

}
