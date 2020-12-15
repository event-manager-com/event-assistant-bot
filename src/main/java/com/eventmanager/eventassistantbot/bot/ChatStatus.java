package com.eventmanager.eventassistantbot.bot;


public enum ChatStatus {
    GROUP_CHAT,
    WAITING_FOR_QUESTION,
    ADMIN_CHAT,
    WAITING_FOR_ANSWER;


    public boolean isGroup() {
        if(this.name().equals("GROUP_CHAT")||this.name().equals("WAITING_FOR_QUESTION")) return true;
        return false;
    }
}
