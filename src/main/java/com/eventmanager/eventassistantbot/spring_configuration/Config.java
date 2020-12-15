package com.eventmanager.eventassistantbot.spring_configuration;


import com.eventmanager.eventassistantbot.bot.EventAssistantBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.time.Duration;

@Configuration
public class Config {
    @Value("${telegram.external-url}")
    private String webHookPath;
    
    @Value("${telegram.BOT_USER_NAME}")
    private String botUserName;
    
    @Value("${telegram.BOT_TOKEN}")
    private String botToken;
    
    @Value("${telegram.proxy.type}")
    private DefaultBotOptions.ProxyType proxyType;
    
    @Value("${telegram.proxy.host}")
    private String proxyHost;
    
    @Value("${telegram.proxy.port}")
    private int proxyPort;
    
    @Value("${rest.template.timeout}")
    private long timeout;

    public static final String TELEGRAM_REGISTRATION_URL="https://api.telegram.org/bot";


    @Bean
    public EventAssistantBot eventAssistantBot() {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);

        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);
        
        EventAssistantBot bot = new EventAssistantBot(options);
        bot.setBOT_TOKEN(botToken);
        bot.setBOT_USER_NAME(botUserName);
        bot.setBOT_PATH(webHookPath);
        RestTemplate restTemplate = new RestTemplate();
       
        restTemplate.exchange(TELEGRAM_REGISTRATION_URL+botToken+"/setWebhook?url="+webHookPath,
                HttpMethod.GET,null,Void.class);
        return bot;
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.setConnectTimeout(Duration.ofMillis(timeout))
                .setReadTimeout(Duration.ofMillis(timeout))
                .build();
    }
}
