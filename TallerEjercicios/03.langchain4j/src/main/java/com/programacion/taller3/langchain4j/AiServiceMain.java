package com.programacion.taller3.langchain4j;

import com.programacion.taller3.langchain4j.utils.AsistenteProgramacion;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;

import static com.programacion.taller3.langchain4j.ChatModelMain.*;

public class AiServiceMain {
  static void main() {

    var chatModel =
        OpenAiChatModel.builder()
            .apiKey(ChatModelMain.KEY)
            .modelName(ChatModelMain.MODEL)
            .baseUrl(ChatModelMain.BASE_URL)
            .logRequests(true)
            //            .logResponses(true)
            .build();

    var asistente = AiServices.create(AsistenteProgramacion.class, chatModel);

    var respuesta = asistente.responderPregunta("¿Qué es LangChain4j?");

    System.out.println(respuesta);

    System.out.println("-------------------------------------------");

    String contexto =
        """
        Los requisitos de titulación son:
        - Aprobar todos los cursos
        - Presentar un proyecto final
        - Aprobar el examen de titulación
        """;

    var respuesta2 = asistente.consultar("Cuales son los requisitos de titulacion", contexto);

    System.out.println(respuesta2);
  }
}
