package com.programacion.taller3.langchain4j.utils;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

@SystemMessage(
    """
         Eres un asistente de programación especializado en Java. Responde de manera clara y concisa."
         Resonde macimo con un parrafo de 5 lineas
    """)
public interface AsistenteProgramacion {
  String responderPregunta(String pregunta);

  @UserMessage(
"""
  Responde la pregunta: {{pregunta}}
  Si no sabes la respuesta, utiliza el contexto proporcionado:
  CONTEXTO:
  {{contexto}}
""")
  String consultar(@V("pregunta") String pregunta, @V("contexto") String contexto);
}
