package com.karinasasaki.gerenciador_de_tarefas.controllers;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

  @PostMapping
  public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa) {
    tarefa = tarefaService.insert(tarefa);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(tarefa.getId()).toUri();
    return ResponseEntity.created(uri).body(tarefa);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
    tarefa = tarefaService.update(id, tarefa);
    return ResponseEntity.ok().body(tarefa);
  }
}
