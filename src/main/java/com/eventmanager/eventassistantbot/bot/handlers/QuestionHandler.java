package com.eventmanager.eventassistantbot.bot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;


@Component
public class QuestionHandler {
    private Map<Long,Long> adminMap=new HashMap<>();


    public SendMessage handleQuestion(Update update) {
        SendMessage message=new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        String question = update.getMessage().getText();
        if(newQuestion(question)){
            sendQuestionToAdmin(update);
            message.setText("Question sent to admin");
        }
        else message.setText(existingAnswers(question));
        return message;
    }

    private String existingAnswers(String question) {
        //todo
        return "existing answer";
    }

    private void sendQuestionToAdmin(Update update) {
        //todo
        SendMessage message=new SendMessage();
        message.setChatId(adminMap.get(update.getMessage().getChatId()).toString());
        String firstName = update.getMessage().getFrom().getFirstName();
        String question = update.getMessage().getText();
        message.setText(firstName + " is asking " + question);
//        need to send it to admin
    }

    private boolean newQuestion(String question) {
        //todo
        return true;
    }
}
