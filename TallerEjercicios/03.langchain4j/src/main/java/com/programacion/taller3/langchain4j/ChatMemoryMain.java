package com.programacion.taller3.langchain4j;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

import java.util.Scanner;

@SystemMessage(
    """
    Responde en español.
    Usando parrafos cortos y claros.

    """)
interface Michat {
  String chat(String message);
}

public class ChatMemoryMain {
  static void main() {
    var chatmodel =
        OpenAiChatModel.builder()
            .apiKey(ChatModelMain.API_KEY)
            .modelName(ChatModelMain.MODEL)
            .baseUrl(ChatModelMain.BASE_URL)
            //                .logRequests(true)
            //                .logResponses(true)
            .build();

    ChatMemory memory = MessageWindowChatMemory.builder().maxMessages(10).build();

    var chatService =
        AiServices.builder(Michat.class).chatModel(chatmodel).chatMemory(memory).build();

    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.print("User: ");
      var input = sc.nextLine();
      if (input.equalsIgnoreCase("exit")) {
        break;
      }
      var response = chatService.chat(input);
      System.out.println("Assistant: " + response);
    }
  }
}
