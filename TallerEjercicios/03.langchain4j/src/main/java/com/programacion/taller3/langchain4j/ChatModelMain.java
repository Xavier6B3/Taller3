package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class ChatModelMain {
  static final String BASE_URL = "https://api.groq.com/openai/v1";
  static final String API_KEY = "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";
  static final String MODEL = "openai/gpt-oss-120b";

  public static void main(String[] args) {
    OpenAiChatModel chatModel =
        OpenAiChatModel.builder()
            .baseUrl(BASE_URL)
            .apiKey(API_KEY)
            .modelName(MODEL)
            .logRequests(true)
            .logResponses(true)
            .build();

    var respuesta = chatModel.chat("Hola, ¿cómo estás?");
    System.out.println(respuesta);
  }
}

 // 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
