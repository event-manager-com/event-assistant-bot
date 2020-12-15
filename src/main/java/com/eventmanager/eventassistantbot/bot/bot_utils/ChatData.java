package com.eventmanager.eventassistantbot.bot.bot_utils;


import com.eventmanager.eventassistantbot.bot.ChatStatus;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class ChatData {
//add eventId
    private Map<Long, ChatStatus> chatMap =new HashMap<>();
    public boolean newChat(Long id) {
        return !chatMap.containsKey(id);
    }

    public void registerChat(Long id, ChatStatus chatStatus) {
        chatMap.put(id,chatStatus);
    }

    public boolean contains(Long chatId) {
        return chatMap.containsKey(chatId);
    }

    public ChatStatus get(Long chatId) {
        return chatMap.get(chatId);
    }

    public void replace(long chatId, ChatStatus chatStatus) {
        chatMap.replace(chatId,chatStatus);
    }
}
