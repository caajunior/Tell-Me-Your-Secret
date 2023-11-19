package com.tellmeyoursecret.secretapi;


public class ChatMessageRequest {
    private Long chatUserId;
    private String message;

    public ChatMessageRequest(Long chatUserId, String message) {
        this.chatUserId = chatUserId;
        this.message = message;
    }
    public Long getChatUserId() {
        return chatUserId;
    }
    public void setChatUserId(Long chatUserId) {
        this.chatUserId = chatUserId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
