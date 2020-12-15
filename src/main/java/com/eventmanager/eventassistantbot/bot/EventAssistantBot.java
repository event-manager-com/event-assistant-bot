package com.eventmanager.eventassistantbot.bot;

import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


@Setter
public class EventAssistantBot extends TelegramWebhookBot {
    private String BOT_USER_NAME;
    private  String BOT_TOKEN ;
    private String BOT_PATH;

    @Autowired
    private TelegramFacade telegramFacade;


    public EventAssistantBot(DefaultBotOptions options){
        super(options);
    }


    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return telegramFacade.updateHandler(update);
    }

    @Override
    public String getBotPath() {
        return BOT_PATH;
    }
}















