package com.workchat.serviceImpl;

import com.workchat.config.WebSocketSessionManager;
import com.workchat.dto.request.UserDto;
import com.workchat.entity.User;
import com.workchat.globalResponse.MessageResponse;
import com.workchat.repository.UserRepository;
import com.workchat.service.CustomPasswordEncoder;
import com.workchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WebSocketSessionManager sessionManager;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Override
    public List<Map<String, Object>> getAllActiveUsers() {
        return sessionManager.getAllActiveSessions();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public MessageResponse saveUser(UserDto dto) {
        try{
            Optional<User> byUsername = userRepo.findByUsername(dto.username());
            if (byUsername.isPresent()) return new MessageResponse("username already in use", HttpStatus.BAD_REQUEST);

            Optional<User> byEmail = userRepo.findByEmail(dto.email());
            if (byEmail.isPresent()) return new MessageResponse("email already in use", HttpStatus.BAD_REQUEST);

            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setUsername(dto.username());
            user.setFullName(dto.fullName());
            user.setEmail(dto.email());
            user.setPassword(passwordEncoder.encode(dto.password()));
            userRepo.save(user);
            return new MessageResponse("user saved successfully", HttpStatus.CREATED);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new MessageResponse("Error saving user",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
