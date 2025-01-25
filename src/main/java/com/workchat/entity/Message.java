package com.workchat.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;
@Data
@Document(collection = "messages")
public class Message {
    @Id
    private String id= UUID.randomUUID().toString();
    private String senderId;
    private String recipientId;
    private String roomId;
    @NotBlank
    private String content;
    private Boolean isRead=false;
    private String timestamp;
}