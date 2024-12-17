package com.example.demo.listeners;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class KafkaListener {

    @Bean
    public Consumer<Message<String>> consume(){
        return msg -> {
            System.out.println(msg.getPayload());
        };
    }

}
