package com.workchat.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class WebSocketSessionManager {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void addSession(String username, WebSocketSession session) {
        sessions.put(username, session);
    }

    public void removeSession(String username) {
        sessions.remove(username);
    }

    public WebSocketSession getSession(String username) {
        return sessions.get(username);
    }

    public List<Map<String, Object>> getAllActiveSessions() {
        return sessions.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> sessionInfo = new HashMap<>();
                    sessionInfo.put("username", entry.getKey());
                    sessionInfo.put("isOpen", entry.getValue().isOpen());
                    return sessionInfo;
                })
                .collect(Collectors.toList());
    }


}

