package com.eventmanager.eventassistantbot.bot.handlers;

import com.eventmanager.eventassistantbot.bot.ChatStatus;
import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.bot_utils.UsersHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupHandler {
    @Autowired
    private UsersHandler usersHandler;
    @Autowired
    private List<GroupButtonHandler> buttons;
    @Autowired
    private QuestionHandler questionHandler;

    public SendMessage handleMessage(Update update, ChatData chatData) {
        SendMessage message = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId.toString());
        User user = update.getMessage().getFrom();
        if(usersHandler.newUser(user, chatId)){
            message.setText(welcomeMessage(user.getFirstName()));
            InlineKeyboardMarkup markupInline = getMenu();
            message.setReplyMarkup(markupInline);
        }else if(chatData.get(chatId) == ChatStatus.WAITING_FOR_QUESTION){
            chatData.replace(chatId,ChatStatus.GROUP_CHAT);
            message=questionHandler.handleQuestion(update);
        }
        return message;
    }


    private InlineKeyboardMarkup getMenu() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        buttons.stream()
                .map(GroupButtonHandler::getButton)
                .forEach(rowInline::add);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    private String welcomeMessage(String firstName) {
        return "Hi "+ firstName+"\nwelcome to the group\n";

    }

    public SendMessage handleButtons(Update update,SendMessage message,ChatData chatData) {
        buttons.forEach(button->button.handle(update,message,chatData));
        return message;

    }
}
