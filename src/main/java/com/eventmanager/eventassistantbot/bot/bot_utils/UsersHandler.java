package com.eventmanager.eventassistantbot.bot.bot_utils;

import com.eventmanager.eventassistantbot.dto.UserDto;
import com.eventmanager.eventassistantbot.services.EventService;
import com.eventmanager.eventassistantbot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsersHandler{
   private Map<Integer,List<Long>> usersCache = new HashMap<>();

   @Autowired
   private UserService userService;
   @Autowired
   private EventService eventService;

    public boolean newUser(User user, Long chatId){
        if(!usersCache.containsKey(user.getId())&&userService.get(user.getId())==null) {
            List<Long> chatList = new ArrayList<>();
            chatList.add(chatId);
//            userService.create(user);
            //*********************************************************************
            eventService.addGuestToEvent(new UserDto(user.getId(),user.getFirstName()),2L);
            usersCache.put(user.getId(),chatList);
            return true;
        }
        else if(!usersCache.get(user.getId()).contains(chatId)){
            usersCache.get(user.getId()).add(chatId);
            return true;
        }
        return false;
    }
}
