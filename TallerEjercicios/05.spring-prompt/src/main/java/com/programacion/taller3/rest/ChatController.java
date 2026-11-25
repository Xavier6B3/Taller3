package com.programacion.taller3.rest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

  final ChatClient chatClient;

  @Value("classpath:/prompts/system-prompt-st")
  Resource systemPrompt;

  @Value("classpath:/prompts/user-prompt-st")
  Resource userPrompt;

  public ChatController(ChatClient.Builder builder) {
    this.chatClient =
        builder
            .defaultAdvisors(
                // LOG request/response del chat
                new SimpleLoggerAdvisor())
            .build();
  }

  @PostMapping(path = "/chat")
  public String chat(@RequestBody ChatRequest request) {
    return chatClient
        .prompt()
        .system(systemSpec -> systemSpec.text(systemPrompt))
        .user(userSpec -> userSpec.text(userPrompt).param("Pregunta", request.message()))
        .call()
        .content();
  }

  // Ejmplo para probar el chat
  // http://localhost:8080/chat?message=Hola
}
