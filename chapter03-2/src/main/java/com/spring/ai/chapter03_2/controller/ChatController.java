package com.spring.ai.chapter03_2.controller;

import com.spring.ai.chapter03_2.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat/openai")
    public ResponseEntity<String> chatWithOpenAi(@RequestParam(value = "q", defaultValue = "안녕")
                                       String query) {
        String response = chatService.getOpenAiResponse(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chat/ollama")
    public ResponseEntity<String> chatWithOllama(@RequestParam(value = "q", defaultValue = "안녕")
                                                 String query) {
        String response = chatService.getOllamaResponse(query);
        return ResponseEntity.ok(response);
    }
}
