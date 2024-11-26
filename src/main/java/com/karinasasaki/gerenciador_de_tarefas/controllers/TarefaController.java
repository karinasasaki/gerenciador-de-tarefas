package com.karinasasaki.gerenciador_de_tarefas.controllers;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

  @GetMapping
  public ResponseEntity<Tarefa> findAll() {
    Tarefa tarefa = new Tarefa("Beber água", null);
    return ResponseEntity.ok().body(tarefa);
  }
}
