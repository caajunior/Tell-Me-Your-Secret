package com.tellmeyoursecret.secretapi;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ChatMessageController {
    private final ChatMessageRepository chatRepository;
    private final ChatUserRepository userRepository;

    public ChatMessageController(ChatMessageRepository chatRepository, ChatUserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/chatmessage")
    List<ChatMessage> all() {
        return chatRepository.findAll();
    }

    @PostMapping("/chatmessage")
    ChatMessage newChatMessage(@RequestBody ChatMessageRequest newChatMessage) {
        return userRepository.findById(newChatMessage.getChatUserId()).map(user -> {
            return chatRepository.save(new ChatMessage(newChatMessage.getMessage(), user));
        })
        .orElseGet(() -> {
            return new ChatMessage();
        });
    }

    @GetMapping("/chatmessage/newmessages/{size}")
    List<ChatMessage> allSized(@PathVariable int size) {
        List<ChatMessage> messages = chatRepository.findAll();

        if (size >= messages.size()) 
            return messages;
        
        return messages.subList(messages.size() - size, messages.size());
    }

    @GetMapping("/chatmessage/{id}")
    ChatMessage one(@PathVariable Long id) {
        return chatRepository.findById(id)
            .orElseThrow(() -> new ChatMessageNotFound(id));  
    }

    @PutMapping("/chatmessage/{id}")
    ChatMessage replaceMessage(@RequestBody ChatMessage newMessage, @PathVariable Long id)
    {
        return chatRepository.findById(id)
            .map(message -> {
                long sentTime = ChronoUnit.MINUTES.between(message.getTimestamp(), LocalTime.now());
                if(Math.abs(sentTime) < 2 && !message.isChanged())
                {
                    message.setMessage(newMessage.getMessage());
                    message.setChanged(true);
                    return chatRepository.save(message);
                }
                return message;
            })
            .orElseGet(() -> {
                newMessage.setId(id);
                return chatRepository.save(newMessage);
            });
    }

    @DeleteMapping("/chatmessage/{id}")
    void deleteChatMessage(@PathVariable Long id) {
        chatRepository.deleteById(id);
    }
}
