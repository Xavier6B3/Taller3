package com.programacion.taller3;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  final ChatClient chatClient;

  public ChatController(ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  @GetMapping(path = "/chat")
  public String chat(@RequestParam String message) {
    return chatClient.prompt().user(message).call().content();
  }

  // Ejmplo para probar el chat
  // http://localhost:8080/chat?message=Hola
}
