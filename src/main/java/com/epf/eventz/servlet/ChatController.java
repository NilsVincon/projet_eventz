package com.epf.eventz.servlet;

import com.epf.eventz.dao.ChatMessageDAO;
import com.epf.eventz.model.ChatMessage;
import com.epf.eventz.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/eventz/chat")
    public String getChat(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username",authentication.getName());
        List<ChatMessage> messages = chatMessageService.findAll();
        model.addAttribute("messages",messages);
        return "chat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        chatMessageService.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}