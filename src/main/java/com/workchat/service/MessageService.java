package com.workchat.service;

import com.workchat.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    void sendMessageToUser(String username, String message, String sender);

    List<Map<String, Object>> getAllActiveUsers();

    List<Message> findChatHistory(String senderId, String recipientId);
}
