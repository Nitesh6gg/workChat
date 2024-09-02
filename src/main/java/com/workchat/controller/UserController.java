package com.workchat.controller;

import com.workchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/login"})
    public String index() {
        return "login";
    }

    @GetMapping("/index")
    public String getAllActiveUsers(Model model) {
        List<Map<String,Object>> activeUsers = userService.getAllActiveUsers();
        model.addAttribute("activeUsers",activeUsers);
        return "index";
    }

    @GetMapping("/chats")
    public String chatPage(@RequestParam String id, Model model) {
        model.addAttribute("userId", id);
        return "chat";
    }

}
