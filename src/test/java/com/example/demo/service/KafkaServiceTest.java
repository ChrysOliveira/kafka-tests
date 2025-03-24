package com.example.demo.service;

import static org.mockito.Mockito.*;

import com.example.demo.domain.MessageDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class KafkaServiceTest {

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private KafkaService kafkaService;

    @Test
    void sendMessage_shouldCallStreamBridgeSendToKafka() {
        String topic = "test-topic";
        MessageDomain messageDomain = new MessageDomain("Hello Kafka");

        Message<MessageDomain> kafkaMessage = MessageBuilder.withPayload(messageDomain).build();

        when(streamBridge.send(anyString(), any(Message.class))).thenReturn(true);

        boolean result = kafkaService.sendMessageToKafka(topic, messageDomain);

        assertTrue(result);
    }
}