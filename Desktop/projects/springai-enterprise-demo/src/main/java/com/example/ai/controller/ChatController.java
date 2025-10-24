package com.example.ai.controller;

import com.example.ai.dto.ChatRequest;
import com.example.ai.dto.ChatResponse;
import com.example.ai.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/ai", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        String answer = chatService.answerWithRag(request.question());
        return new ChatResponse(answer);
    }
}
