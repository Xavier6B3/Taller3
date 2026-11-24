package com.programacion.taller3.embedding;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.*;

public class TokenizerMain {
  public static void main(String[] args) {
    EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();

    Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_001);

    var text = "Hello, do you like tea?";

    // Encode
    IntArrayList tokensIds = tokenizer.encode(text);

    System.out.println("Total tokens: " + tokensIds.size());
    System.out.println(tokensIds);

    // Decode
    String textDecode = tokenizer.decode(tokensIds);
    System.out.println("Decoded text: " + textDecode);
  }
}
