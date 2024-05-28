package com.epf.eventz.model;

import com.epf.eventz.chat.MessageType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_chat;
    private MessageType type;
    private String content;
    private String sender;
    private String destination;
    @ManyToOne
    private Evenement evenement;

}
