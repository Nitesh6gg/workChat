package com.workchat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "messages")
public class Message {
    @Id
    private String id= UUID.randomUUID().toString();
    private String senderId;
    private String recipientId;
    private String roomId;
    private String message;
    private Boolean isRead=false;
    private LocalDateTime createdAt=LocalDateTime.now();

}