package com.programacion.taller3.langchain4j;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;

import java.util.Scanner;

@SystemMessage("Responde con un solo parrafo claro y corto")
interface MiChat {
  Result<String> chat(String mensaje);
}

public class ChatMemoryMain {
  static void main() {
    var chatModel =
        OpenAiChatModel.builder()
            .apiKey(ChatModelMain.KEY)
            .modelName(ChatModelMain.MODEL)
            .baseUrl(ChatModelMain.BASE_URL)
            .logRequests(true)
            //            .logResponses(true)
            .build();

    ChatMemory memory = MessageWindowChatMemory.builder().maxMessages(10).build();

    var chat = AiServices.builder(MiChat.class).chatModel(chatModel).chatMemory(memory).build();

    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("mensaje>");
      String mensaje = scanner.nextLine();
      if ("exit".equalsIgnoreCase(mensaje)) {
        break;
      }

      Result<String> respuesta = chat.chat(mensaje);

      var texto = "tokens: %s repsuesta=%s".formatted(respuesta.tokenUsage(), respuesta.content());
      System.out.println("Respuesta> " + texto);
    }
  }
}
