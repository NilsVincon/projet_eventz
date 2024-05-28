package com.epf.eventz.service;

import com.epf.eventz.dao.ChatMessageDAO;
import com.epf.eventz.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageDAO chatMessageDAO;

    public List<ChatMessage> findAll() {
        return chatMessageDAO.findAll();
    }

    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageDAO.save(chatMessage);
    }
}