package com.workchat.controller;

import com.workchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/message")
    public String getAllActiveUsers(Model model) {
        List<Map<String,Object>> activeUsers = userService.getAllActiveUsers();
        model.addAttribute("activeUsers",activeUsers);
        return "message";
    }

    @GetMapping("/chats/{id}")
    public String chatPage(@PathVariable String id, Model model) {
        model.addAttribute("userId", id);
        return "chat";
    }

}
