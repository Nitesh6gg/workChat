package com.workchat.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workchat.service.MessageService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

/*
    private final WebSocketSessionManager sessionManager;

    public ChatWebSocketHandler(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String username = (String) session.getAttributes().get("username");
        sessionManager.addSession(username,session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String username = (String) session.getAttributes().get("username");
        sessionManager.removeSession(username);
    }
}
*/

    private static final Logger logger = Logger.getLogger(ChatWebSocketHandler.class.getName());

    private final WebSocketSessionManager sessionManager;
    private final MessageService messageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatWebSocketHandler(WebSocketSessionManager sessionManager, MessageService messageService) {
        this.sessionManager = sessionManager;
        this.messageService = messageService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Do not register the session yet. Wait for LOGIN message
        logger.info("New WebSocket connection established.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            JsonNode jsonNode = objectMapper.readTree(message.getPayload());
            String type = jsonNode.get("type").asText();

            if ("LOGIN".equals(type)) {
                String username = jsonNode.get("username").asText();
                sessionManager.addSession(username, session);
                session.getAttributes().put("username", username);
                logger.info("User logged in: " + username);
            } else if ("MESSAGE".equals(type)) {
                String recipient = jsonNode.get("recipient").asText();
                String content = jsonNode.get("content").asText();
                String sender = jsonNode.get("sender").asText();

                // Save message to database if needed
                // Optionally, you can implement saving logic here

                // Send message to recipient
                messageService.sendMessageToUser(recipient, content, sender);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error parsing message: " + message.getPayload(), e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String username = (String) session.getAttributes().get("username");
        if (username != null) {
            sessionManager.removeSession(username);
            logger.info("User disconnected: " + username);
        }
    }
}
