package com.epf.eventz.service;

import com.epf.eventz.dao.ChatMessageDAO;
import com.epf.eventz.model.ChatMessage;
import com.epf.eventz.model.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageDAO chatMessageDAO;

    public List<ChatMessage> findAll() {
        return chatMessageDAO.findAll();
    }

    public List<ChatMessage> findByDestination(String destination) {
        return chatMessageDAO.findByDestination(destination);
    }


    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageDAO.save(chatMessage);
    }

    public ChatMessage censorMessage(ChatMessage chatMessage) {
        List<String> banwords = Arrays.asList("insulte","grosmot","gros mot");
        String content = chatMessage.getContent();
        content = content.toLowerCase();
        int count = 0;
        for (String banword : banwords) {
            String asterisks = "*".repeat(banword.length());
            if (content.contains(banword)) {
                content = content.replaceAll(banword, asterisks);
                count++;
            }
        }
        chatMessage.setContent(content);
        return chatMessage;
    }
}