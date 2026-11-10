package com.programacion.taller3.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;
import java.util.List;

public class BusquedaSemanticaMain {
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

    List<Embedding> embeddings =
        documentos.stream().map(model::embed).map(Response::content).toList();

    var consulta = "Programacion orientada a objetos";
    var embeddingConsulta = model.embed(consulta).content();

    int index = 0;
    int mejorIndex = 0;
    double mejorScore = -1.0;

    for (var it : embeddings) {
      double score = CosineSimilarity.between(embeddingConsulta, it);
      System.out.printf("Documento %d - Similitud %.4f%n", index, score);
      if (score > mejorScore) {
        mejorScore = score;
        mejorIndex = index;
      }
      index++;
    }
    System.out.printf("mejor resultado: [%d] - %s%n", mejorIndex, documentos.get(mejorIndex));
    System.out.println("mejor score: " + mejorScore);
  }
}
