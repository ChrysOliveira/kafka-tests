package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StreamBridge streamBridge) {
		return args -> {
			for (int i = 0; i < 10; i++) {
				Message<String> mensagem = MessageBuilder.withPayload("Chrystian Oliveira").build();
				streamBridge.send("producer-out-0", mensagem);
			}
		};
	}

}
