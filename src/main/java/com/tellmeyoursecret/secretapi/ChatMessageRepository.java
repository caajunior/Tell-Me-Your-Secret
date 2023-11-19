package com.tellmeyoursecret.secretapi;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    void deleteByTimestampBefore(LocalTime timestamp);
}