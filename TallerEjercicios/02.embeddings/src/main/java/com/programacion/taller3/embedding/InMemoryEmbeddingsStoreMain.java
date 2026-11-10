package com.programacion.taller3.embedding;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.Paths;
import java.util.List;

public class InMemoryEmbeddingsStoreMain {
  public static void main(String[] args) {
    var pathModel =
        Paths.get(
            "/home/labcom/IdeaProjects/Taller3/Taller3_Ejercicios/TallerEjercicios/modelos/model.onnx");
    var pathTokenizer =
        Paths.get(
            "/home/labcom/IdeaProjects/Taller3/Taller3_Ejercicios/TallerEjercicios/modelos/tokenizer.json");
    OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);

    var documentos =
        List.of(
            "Java es un lenguaje de programacion orientado a objetos",
            "Python es un lenguaje de programacion interpretado",
            "JavaScript es un lenguaje de programacion scripting");

    InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();

    documentos.forEach(it -> {
      String key = "doc-" + (store.size() + 1);
      var embedding = model.embed(it);
      store.add(key, embedding.content(), TextSegment.from(it));
    });

    // consulta
    var consulta = "Programacion orientada a objetos";
    var searchQuery =
        EmbeddingSearchRequest.builder()
            .queryEmbedding(model.embed(consulta).content())
            .maxResults(2) // top-k
            .build();

    EmbeddingSearchResult<TextSegment> searchResult = store.search(searchQuery);

    for (var it : searchResult.matches()) {
      System.out.printf("Score: %.4f - %s%n", it.score(), it.embedded().text());
    }
  }
}
