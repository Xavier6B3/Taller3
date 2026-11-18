package com.programacion.taller3.langchain4j;

import com.programacion.taller3.langchain4j.utils.MyStreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import java.util.concurrent.atomic.AtomicInteger;

import static com.programacion.taller3.langchain4j.ChatModelMain.*;

public class ChatStreamModelMain {
  public static void main() throws Exception {
    var chatModel =
        OpenAiStreamingChatModel.builder()
            .apiKey(ChatModelMain.KEY)
            .baseUrl(ChatModelMain.BASE_URL)
            .modelName(ChatModelMain.MODEL)
            .build();

    var count = new AtomicInteger(1);

    chatModel.chat("cuando es la 3ra guerra mundial?", new MyStreamingChatResponseHandler(count));

    while (count.get() > 0) {
      Thread.sleep(100);
    }
  }
}
