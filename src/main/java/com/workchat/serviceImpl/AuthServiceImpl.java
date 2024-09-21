package com.workchat.serviceImpl;

import com.workchat.dto.request.LoginDto;
import com.workchat.entity.User;
import com.workchat.globalResponse.MessageResponse;
import com.workchat.repository.UserRepository;
import com.workchat.service.AuthService;
import com.workchat.service.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;
    @Override
    public MessageResponse authenticate(LoginDto dto) {
        try{
            Optional<User> byUsername = userRepo.findByUsername(dto.username());
            if(byUsername.isPresent()){
                User user = byUsername.get();
                if(passwordEncoder.matches(dto.password(), user.getPassword())){
                    return new MessageResponse("Login Successful", HttpStatus.OK);
                }else{
                    return new MessageResponse("Invalid Credentials",HttpStatus.BAD_REQUEST);
                }
            }else{
                return new MessageResponse("Username Not Found", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new MessageResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
