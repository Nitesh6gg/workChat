package com.workchat.serviceImpl;

import com.workchat.config.WebSocketSessionManager;
import com.workchat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final WebSocketSessionManager sessionManager;

    @Autowired
    public MessageServiceImpl(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void sendMessageToUser(String username, String message, String sender) {
        WebSocketSession session = sessionManager.getSession(username);
        if (session != null && session.isOpen()) {
            try {
                // Create a JSON message to send
                String jsonMessage = String.format("{\"type\":\"MESSAGE\",\"sender\":\"%s\",\"content\":\"%s\"}", sender, message);
                session.sendMessage(new TextMessage(jsonMessage));
            } catch (IOException e) {
                log.warn("Failed to send message to user: " + username, e);
            }
        } else {
            log.warn("Session not found or closed for user: " + username);
        }
    }

    @Override
    public List<Map<String, Object>> getAllActiveUsers() {
        return sessionManager.getAllActiveSessions();
    }
}
