package com.workchat.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String senderId;
    private String recipientId;
    private String roomId;
    private String message;
    private Boolean isRead;
    private String createdAt;

}