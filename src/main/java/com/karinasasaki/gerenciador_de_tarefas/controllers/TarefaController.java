package com.karinasasaki.gerenciador_de_tarefas.controllers;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {


  @Autowired
  private TarefaService tarefaService;

  @GetMapping
  public ResponseEntity<List<Tarefa>> listarTarefas() {
    List<Tarefa> tarefas = tarefaService.findAll();
    return ResponseEntity.ok().body(tarefas);
  }
}
