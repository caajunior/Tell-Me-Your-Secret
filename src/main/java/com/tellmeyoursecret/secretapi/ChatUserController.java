package com.tellmeyoursecret.secretapi;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ChatUserController {
    private final ChatUserRepository repository;

    public ChatUserController(ChatUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/chatuser")
    List<ChatUser> all() {
        return repository.findAll();
    }

    @PostMapping("/chatuser")
    ChatUser newChatUser(@RequestBody ChatUser newChatUser) {
        return repository.save(newChatUser);
    }

    @GetMapping("/chatuser/{id}")
    ChatUser one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ChatMessageNotFound(id));  
    }

    @PutMapping("/chatuser/{id}")
    ChatUser replaceUser(@RequestBody ChatUser newChatUser, @PathVariable Long id)
    {
        return repository.findById(id)
            .map(user -> {
                user.setName(newChatUser.getName());
                return repository.save(user);
            })
            .orElseGet(() -> {
                newChatUser.setId(id);
                return repository.save(newChatUser);
            });
    }

    @DeleteMapping("/chatuser/{id}")
    void deleteChatUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
