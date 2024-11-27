package com.karinasasaki.gerenciador_de_tarefas.controllers;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.services.TarefaService;
import jakarta.validation.Valid;
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
  private TarefaService service;

  @GetMapping
  public ResponseEntity<List<Tarefa>> listarTarefas() {
    List<Tarefa> tarefas = service.findAll();
    return ResponseEntity.ok().body(tarefas);
  }

  @PostMapping
  public ResponseEntity<Tarefa> adicionarTarefa(@Valid @RequestBody Tarefa tarefa) {
    tarefa = service.insert(tarefa);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(tarefa.getId()).toUri();
    return ResponseEntity.created(uri).body(tarefa);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> excluirTarefa(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Integer id, @RequestBody Tarefa tarefa) {
    tarefa = service.update(id, tarefa);
    return ResponseEntity.ok().body(tarefa);
  }
}