package com.tellmeyoursecret.secretapi;

import java.time.LocalTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
class ChatMessage {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String message;
    private boolean changed;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private ChatUser sender;

    @Column(updatable = false)
    private @CurrentTimestamp LocalTime timestamp;

    public ChatMessage() {
        this.message = "";
        this.changed = false;
     }

    public ChatMessage(String message, ChatUser sender) {
        this.message = message;
        this.changed = false;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public ChatUser getSender() {
        return sender;
    }

    public void setSender(ChatUser sender) {
        this.sender = sender;
    }
}
