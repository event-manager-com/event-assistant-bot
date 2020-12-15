package com.eventmanager.eventassistantbot.controller;



import com.eventmanager.eventassistantbot.bot.EventAssistantBot;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;
        import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
        import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class webHookController {
    @Autowired
    private EventAssistantBot eventAssistantBot;
    @RequestMapping(value="/",method= RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return eventAssistantBot.onWebhookUpdateReceived(update);
    }
}