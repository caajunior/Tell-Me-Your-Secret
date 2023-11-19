package com.tellmeyoursecret.secretapi;

public class ChatMessageNotFound extends RuntimeException {

    ChatMessageNotFound(Long id) {
        super("Could not find message " + id);
    }
}
