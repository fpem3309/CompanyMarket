package com.example.companymarket;

public class Chat {
    private String chat_userName;
    private String chat_userMessage;
    private String chat_userTime;

    public Chat(){

    }

    public Chat(String chat_userName, String chat_userMessage, String chat_userTime) {
        this.chat_userName = chat_userName;
        this.chat_userMessage = chat_userMessage;
        this.chat_userTime = chat_userTime;
    }

    public String getChat_userMessage() {
        return chat_userMessage;
    }

    public void setChat_userMessage(String chat_userMessage) {
        this.chat_userMessage = chat_userMessage;
    }

    public String getChat_userName() {
        return chat_userName;
    }

    public void setChat_userName(String chat_userName) {
        this.chat_userName = chat_userName;
    }


    public String getChat_userTime() {
        return chat_userTime;
    }

    public void setChat_userTime(String chat_userTime) {
        this.chat_userTime = chat_userTime;
    }
}

