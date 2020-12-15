package com.eventmanager.eventassistantbot.services;


import com.eventmanager.eventassistantbot.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${event.service.url}")
    private String eventServiceUrl;

    @SneakyThrows
    public void addGuestToEvent(UserDto user,Long eventId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String userJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        ResponseEntity<UserDto[]> exchange = restTemplate.exchange(eventServiceUrl + "/guest/" + eventId, HttpMethod.POST, new HttpEntity<>(userJson, httpHeaders), UserDto[].class);
        

    }
}
