package com.karinasasaki.gerenciador_de_tarefas.controllers;

import com.karinasasaki.gerenciador_de_tarefas.controllers.dtos.AtualizarTarefaDTO;
import com.karinasasaki.gerenciador_de_tarefas.controllers.dtos.CriarTarefaDTO;
import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "tarefas")
public class TarefaController {

  @Autowired
  private TarefaService service;

  @GetMapping
  public ResponseEntity<Page<Tarefa>> listarTarefas(
      @RequestParam(defaultValue = "0") Integer pagina,
      @RequestParam(defaultValue = "10") Integer tamanhoPagina) {
    Page<Tarefa> tarefas = service.listarTarefas(pagina, tamanhoPagina);
    return ResponseEntity.ok().body(tarefas);
  }

  @PostMapping
  public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody CriarTarefaDTO dto) {
    Tarefa tarefa = service.criarTarefa(dto);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(tarefa.getId())
        .toUri();
    return ResponseEntity.created(uri).body(tarefa);
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<Void> excluirTarefa(@PathVariable Integer id) {
    service.excluirTarefa(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Integer id, @RequestBody AtualizarTarefaDTO dto) {
    Tarefa tarefa = service.atualizarTarefa(id, dto);
    return ResponseEntity.ok().body(tarefa);
  }
}