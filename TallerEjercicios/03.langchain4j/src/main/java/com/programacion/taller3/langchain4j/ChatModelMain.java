package com.programacion.taller3.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class ChatModelMain {

  static final String BASE_URL = "https://api.groq.com/openai/v1";
  static final String KEY = "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";
  static final String MODEL = "qwen/qwen3.8-27b";

  static void main() {
    System.getenv("OPENAI_BASE_URL");
    System.getenv("OPENAI_API_KEY");

    OpenAiChatModel chatModel =
        OpenAiChatModel.builder()
            .baseUrl(BASE_URL)
            .apiKey(KEY)
            .modelName(MODEL)
            .logRequests(true)
            .logResponses(true)
            .build();

    String respuesta = chatModel.chat("cuando es la 3ra guerra mundial?");
    System.out.println("Respuesta del modelo:");
    System.out.println(respuesta);
  }
  // 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
}
