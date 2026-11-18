package com.programacion.taller3.langchain4j.utils;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class MyStreamingChatResponseHandler implements StreamingChatResponseHandler {

  private final AtomicInteger count;

  public MyStreamingChatResponseHandler(AtomicInteger count) {
    this.count = count;
  }

  @Override
  public void onPartialResponse(String partialResponse) {
    System.out.print(partialResponse);
  }

  @Override
  public void onCompleteResponse(ChatResponse completeResponse) {
    System.out.println();
    System.out.println("Streaming completed successfully");
    count.getAndDecrement();
  }

  @Override
  public void onError(Throwable error) {
    System.err.println();
    System.err.println("Error during streaming: " + error.getMessage());
    error.printStackTrace();
    count.getAndDecrement();
  }
}
