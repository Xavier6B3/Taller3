package com.programacion.taller3.embedding;

import java.nio.file.Paths;

import dev.langchain4j.data.embedding.Embedding;

import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

public class EmbeddingsModelMain {
  static void main() {
    var pathModel =
        Paths.get(
            "/home/labcom/IdeaProjects/Taller3/Taller3_Ejercicios/TallerEjercicios/modelos/model.onnx");
    var pathTokenizer =
        Paths.get(
            "/home/labcom/IdeaProjects/Taller3/Taller3_Ejercicios/TallerEjercicios/modelos/tokenizer.json");
    OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathModel, pathTokenizer, PoolingMode.MEAN);

    var text = "Hola como estas?";
    Response<Embedding> response = model.embed(text);
    Embedding embedding = response.content();
    System.out.println("DImension of the embedding: " + response);
    System.out.println("Embedding vectpr: " + embedding.vectorAsList());
  }
}
