package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "chrysppTopic", groupId = "myGroup")
    void listener(String data){
        System.out.println("Received: " + data);
    }

}
