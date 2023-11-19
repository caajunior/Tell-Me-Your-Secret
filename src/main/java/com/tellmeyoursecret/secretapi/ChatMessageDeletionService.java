package com.tellmeyoursecret.secretapi;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ChatMessageDeletionService {
    
    @Autowired
    private ChatMessageRepository repository;

    @Transactional
    @Scheduled(fixedRate = 600000) // 600,000 ms = 10 min
    public void deletePastMessagesTask() {
        LocalTime deletionTimestamp = LocalTime.now().minus(5, ChronoUnit.MINUTES);
        repository.deleteByTimestampBefore(deletionTimestamp);

    } 
}
