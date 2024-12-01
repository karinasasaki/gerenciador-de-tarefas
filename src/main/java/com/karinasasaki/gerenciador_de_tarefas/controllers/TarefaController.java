package com.karinasasaki.gerenciador_de_tarefas.controllers;

import com.karinasasaki.gerenciador_de_tarefas.controllers.dtos.AtualizarTarefaDTO;
import com.karinasasaki.gerenciador_de_tarefas.controllers.dtos.CriarTarefaDTO;
import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "tarefas")
@Tag(name = "Tarefas")
@Slf4j
public class TarefaController {

  @Autowired
  private TarefaService service;

  @GetMapping
  @Operation(summary = "Listar", description = "Endpoint para listar as tarefas paginadas.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Listado com sucesso.")
  })
  public ResponseEntity<Page<Tarefa>> listarTarefas(
      @RequestParam(defaultValue = "0") Integer pagina,
      @RequestParam(defaultValue = "10") Integer tamanhoPagina) {
    log.info("Listando tarefas");

    Page<Tarefa> tarefas = service.listarTarefas(pagina, tamanhoPagina);
    return ResponseEntity.ok().body(tarefas);
  }

  @PostMapping
  @Operation(summary = "Criar", description = "Endpoint para criar uma nova tarefa.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
      @ApiResponse(responseCode = "400", description = "Erro de validação.")
  })
  public ResponseEntity<Tarefa> criarTarefa(@RequestBody CriarTarefaDTO dto) {
    log.info("Criando tarefa");

    Tarefa tarefa = service.criarTarefa(dto);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(tarefa.getId())
        .toUri();
    return ResponseEntity.created(uri).body(tarefa);
  }

  @DeleteMapping(value = "{id}")
  @Operation(summary = "Excluir", description = "Endpoint para excluir uma tarefa.")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Excluído com sucesso.")
  })
  public ResponseEntity<Void> excluirTarefa(@PathVariable Integer id) {
    log.info("Excluindo tarefa id: {}", id);

    service.excluirTarefa(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "{id}")
  @Operation(summary = "Atualizar", description = "Endpoint para atualizar uma tarefa.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
      @ApiResponse(responseCode = "400", description = "Erro de validação."),
      @ApiResponse(responseCode = "404", description = "Não encontrado.")
  })
  public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Integer id, @Valid @RequestBody AtualizarTarefaDTO dto) {
    log.info("Atualizando tarefa id: {}", id);

    Tarefa tarefa = service.atualizarTarefa(id, dto);
    return ResponseEntity.ok().body(tarefa);
  }
}