package com.eventmanager.eventassistantbot.bot.handlers;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AdminHandler {
    public SendMessage handleMessage(Update update) {
        return new SendMessage();
    }
}
