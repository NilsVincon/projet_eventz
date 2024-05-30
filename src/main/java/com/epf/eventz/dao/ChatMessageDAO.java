package com.epf.eventz.dao;

import com.epf.eventz.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageDAO extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByDestination(String destination);
}
