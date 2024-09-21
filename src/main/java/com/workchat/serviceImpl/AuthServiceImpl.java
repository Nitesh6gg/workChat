package com.workchat.serviceImpl;

import com.workchat.dto.request.LoginDto;
import com.workchat.entity.User;
import com.workchat.globalResponse.LoginResponse;
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
    public LoginResponse authenticate(LoginDto dto) {
        try{
            Optional<User> byEmail = userRepo.findByEmail(dto.email());
            if(byEmail.isPresent()){
                User user = byEmail.get();
                if(passwordEncoder.matches(dto.password(), user.getPassword())){
                    return new LoginResponse("Login Successful", HttpStatus.OK,user);
                }else{
                    return new LoginResponse("Invalid Credentials",HttpStatus.BAD_REQUEST,null);
                }
            }else{
                return new LoginResponse("Email Not Found", HttpStatus.NOT_FOUND,null);
            }
        }catch(Exception e){
            return new LoginResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

}
