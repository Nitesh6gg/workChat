package com.workchat.serviceImpl;

import com.workchat.config.WebSocketSessionManager;
import com.workchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private WebSocketSessionManager sessionManager;

    @Override
    public List<Map<String, Object>> getAllActiveUsers() {
        return sessionManager.getAllActiveSessions();
    }
}
