package com.eventmanager.eventassistantbot.bot;


import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.AdminHandler;
import com.eventmanager.eventassistantbot.bot.handlers.GroupHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.*;

@Component
public class TelegramFacade {
    @Autowired
    private GroupHandler groupHandler;
    @Autowired
    private AdminHandler adminHandler;
    @Autowired
    private ChatData chatData;



    public SendMessage updateHandler(Update update) {
        SendMessage message= new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChat().getId();
            if (update.getMessage().getText().equals("/start")) {
                if (!update.getMessage().getChat().isGroupChat()) {
                    chatData.registerChat(chatId,ADMIN_CHAT);
                }
            }
            else if(update.getMessage().getChat().isGroupChat()&&chatData.newChat(chatId)){
                chatData.registerChat(chatId,GROUP_CHAT);
            }
            if(chatData.contains(chatId)&&chatData.get(chatId).isGroup())
                return groupHandler.handleMessage(update, chatData);
            else
                return adminHandler.handleMessage(update);
        }
        else if (update.hasCallbackQuery()){
            return groupHandler.handleButtons(update,message,chatData);
        }
     return new SendMessage();
    }
}
