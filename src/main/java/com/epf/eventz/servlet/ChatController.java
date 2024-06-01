package com.epf.eventz.servlet;

import com.epf.eventz.exception.ServiceException;
import com.epf.eventz.model.ChatMessage;
import com.epf.eventz.model.Evenement;
import com.epf.eventz.model.Utilisateur;
import com.epf.eventz.service.ChatMessageService;
import com.epf.eventz.service.EvenementService;
import com.epf.eventz.service.ParticipeService;
import com.epf.eventz.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ParticipeService participeService;

    //GENERAL CHAT :

    @GetMapping("/eventz/generalchat")
    public String getGeneralChat(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Utilisateur> utilisateurOptional = null;
        try {
            utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        if (utilisateurOptional.isPresent()){
            Utilisateur utilisateur = utilisateurOptional.get();
            model.addAttribute("user", utilisateur );
        }
        List<ChatMessage> messages = chatMessageService.findByDestination("General Chat");
        model.addAttribute("messages", messages);
        return "generalchat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendGeneralMessage(
            @Payload ChatMessage chatMessage
    ) {
        chatMessageService.censorMessage(chatMessage);
        chatMessageService.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addGeneralUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    // EVENT CHAT

    @GetMapping("/eventz/eventchat/{evenementid}")
    public String getEventChat(Model model, @PathVariable String evenementid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long eventid = Long.parseLong(evenementid);
        Boolean authorization = false;
        try {
            Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurAvecname(authentication.getName());
            Optional<Evenement> evenementOptional = evenementService.findEvenementById(eventid);
            if (utilisateurOptional.isPresent() && evenementOptional.isPresent()){
                Evenement evenement = evenementOptional.get();
                Utilisateur utilisateur = utilisateurOptional.get();
                if (participeService.existsByUtilisateurAndEvenement(utilisateur,evenement)){
                    model.addAttribute("eventname", evenement.getNom_evenement());
                    String nom_evenement = evenement.getNom_evenement();
                    nom_evenement = nom_evenement.replaceAll("\\s+", "").toLowerCase();
                    List<ChatMessage> messages = chatMessageService.findByDestination(nom_evenement);
                    model.addAttribute("messages", messages);
                    model.addAttribute("user", utilisateur);
                    authorization = true;
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("authorization", authorization);
        return "eventchat";
    }

    @MessageMapping("/chat.sendMessage/{eventname}")
    @SendTo("/topic/{eventname}")
    public ChatMessage sendEventMessage(
            @Payload ChatMessage chatMessage
    ) {
        chatMessageService.censorMessage(chatMessage);
        chatMessageService.save(chatMessage);
        return chatMessage;
    }


    @MessageMapping("/chat.addUser/{eventname}")
    @SendTo("/topic/{eventname}")
    public ChatMessage addEventUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor,
            @DestinationVariable String eventname
    ) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}