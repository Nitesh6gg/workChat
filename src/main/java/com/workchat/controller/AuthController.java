package com.workchat.controller;

import com.workchat.dto.request.LoginDto;
import com.workchat.globalResponse.LoginResponse;
import com.workchat.globalResponse.MessageResponse;
import com.workchat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

   @Autowired
   private AuthService authService;

    @PostMapping()
    ResponseEntity<LoginResponse> auth(@RequestBody LoginDto dto){
        LoginResponse response = authService.authenticate(dto);
        return new ResponseEntity<>(response, (HttpStatusCode) response.getHttpStatus());
    }

}
