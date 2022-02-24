package com.example.companymarket;

public class Chat {
    private String chat_userMame;
    private String chat_userMessage;
    private String chat_userTime;


    public String getChat_userMame() {
        return chat_userMame;
    }

    public void setChat_userMame(String chat_userMame) {
        this.chat_userMame = chat_userMame;
    }

    public String getChat_userMessage() {
        return chat_userMessage;
    }

    public void setChat_userMessage(String chat_userMessage) {
        this.chat_userMessage = chat_userMessage;
    }

    public String getChat_userTime() {
        return chat_userTime;
    }

    public void setChat_userTime(String chat_userTime) {
        this.chat_userTime = chat_userTime;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chat_userMame='" + chat_userMame + '\'' +
                ", chat_userMessage='" + chat_userMessage + '\'' +
                ", chat_userTime='" + chat_userTime + '\'' +
                '}';
    }

    public Chat(String chat_userMame, String chat_userMessage, String chat_userTime) {
        this.chat_userMame = chat_userMame;
        this.chat_userMessage = chat_userMessage;
        this.chat_userTime = chat_userTime;
    }
}
