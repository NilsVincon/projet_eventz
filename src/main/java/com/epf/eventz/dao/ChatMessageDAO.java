package com.epf.eventz.dao;

import com.epf.eventz.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageDAO extends JpaRepository<ChatMessage, Long> {
}
