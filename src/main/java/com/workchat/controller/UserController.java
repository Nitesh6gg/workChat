package com.workchat.controller;

import com.workchat.dto.request.UserDto;
import com.workchat.entity.User;
import com.workchat.globalResponse.MessageResponse;
import com.workchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    ResponseEntity<List<User>>getAllUsers(@RequestParam String userId){
        return ResponseEntity.ok(userService.getAllUsers(userId));
    }

    @GetMapping("/active")
    public List<Map<String,Object>> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }

    @PostMapping()
    ResponseEntity<MessageResponse>saveUser(@RequestBody UserDto dto){
        MessageResponse response=userService.saveUser(dto);
        return new ResponseEntity<>(response, (HttpStatusCode) response.getHttpStatus());
    }




}
