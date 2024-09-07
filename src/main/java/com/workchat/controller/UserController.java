package com.workchat.controller;

import com.workchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/active")
    public List<Map<String,Object>> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }


}
