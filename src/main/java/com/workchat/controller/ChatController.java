package com.workchat.controller;

import com.workchat.entity.Message;
import com.workchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final MessageService messageService;

    @GetMapping()
    ResponseEntity<List<Message>> getMessages(@RequestParam String senderId, @RequestParam String recipientId) {
        return ResponseEntity.ok(messageService.findChatHistory(senderId, recipientId));
    }

    @PostMapping("/sendToUser")
    public void sendToUser(@RequestParam String username, @RequestParam String message,@RequestParam String sender) {
        messageService.sendMessageToUser(username, message,sender);
    }

}
