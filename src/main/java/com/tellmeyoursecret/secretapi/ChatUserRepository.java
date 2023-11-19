package com.tellmeyoursecret.secretapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChatUserRepository extends JpaRepository<ChatUser, Long>{
    
}
