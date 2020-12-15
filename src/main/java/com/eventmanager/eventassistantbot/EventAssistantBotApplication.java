package com.eventmanager.eventassistantbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EventAssistantBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventAssistantBotApplication.class, args);
    }
}
