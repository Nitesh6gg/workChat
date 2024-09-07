package com.workchat.controller;

import com.workchat.service.MessageService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendToUser")
    public void sendToUser(@RequestParam String username, @RequestParam String message,@RequestParam String sender) {
        messageService.sendMessageToUser(username, message,sender);
    }

}
