package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

record Persona(String nombre, int edad, String telefono, String ciudad) {}

interface Extractor {
  @UserMessage("Extrae la información de contacto del siguiente texto: {{texto}}")
  Persona extraerInformacionContacto(@V("texto") String texto);
}

public class StructureOutputMain {

  public static void main(String[] args) {
    var chatModel =
        OpenAiChatModel.builder()
            .apiKey(ChatModelMain.API_KEY)
            .modelName(ChatModelMain.MODEL)
            .baseUrl(ChatModelMain.BASE_URL)
            .logRequests(true)
            .logResponses(true)
            .build();
    var extractor = AiServices.builder(Extractor.class).chatModel(chatModel).build();

    var persona =
        extractor.extraerInformacionContacto(
            """
            Hola mi nombre es Juan Perez, 25 años, 123456789, puedes contactarme al correo juan.perez@email.com y soy de Quito\
            """);

    System.out.println(persona);
  }
}
