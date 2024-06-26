package com.epf.eventz.dao;

import com.epf.eventz.model.ChatMessage;
import com.epf.eventz.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatMessageDAO extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByDestination(String destination);
}
