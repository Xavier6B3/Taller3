package com.programacion.taller3.langchain4j;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

import java.time.LocalDateTime;

class MisHerramientas {
  @Tool("Obtiene la hora y fecha actual")
  public String obtenerHoraFecha() {

    return LocalDateTime.now().toString();
  }
}

interface MiAgente {
  @SystemMessage(
      """
      Eres un asistente que utiliza todas las herramientas disponibles para responder las preguntas del usuario.
      """)
  String chat(String message);
}

public class FunctionCallingMain {
  static void main() {
    var chatmodel =
        OpenAiChatModel.builder()
            .apiKey(ChatModelMain.API_KEY)
            .modelName(ChatModelMain.MODEL)
            .baseUrl(ChatModelMain.BASE_URL)
            .logRequests(true)
            .logResponses(true)
            .build();

    var asistente =
        AiServices.builder((MiAgente.class))
            .chatModel(chatmodel)
            .tools(new MisHerramientas())
            .build();

    var respuesta = asistente.chat("que hora es?");
    System.out.println(respuesta);
  }
}
