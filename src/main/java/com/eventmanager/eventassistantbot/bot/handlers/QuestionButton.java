package com.eventmanager.eventassistantbot.bot.handlers;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.WAITING_FOR_QUESTION;

@Component
public class QuestionButton implements GroupButtonHandler{


    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        String callData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        if (callData.equals("ask_question")) {
            chatData.replace(chatId,WAITING_FOR_QUESTION);
            message.setChatId(Long.toString(chatId));
            message.setText("What would you like to ask?");
        }
    }

    @Override
    public InlineKeyboardButton getButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Ask a question");
        button.setCallbackData("ask_question");
        return button;
    }
}
