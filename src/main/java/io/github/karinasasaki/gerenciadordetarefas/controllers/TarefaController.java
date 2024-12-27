package io.github.karinasasaki.gerenciadordetarefas.controllers;

import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.AtualizarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.CriarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.RetornoTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import io.github.karinasasaki.gerenciadordetarefas.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

  private final TarefaService service;

  TarefaController(TarefaService service) {
    this.service = service;
  }

  @GetMapping
  @Operation(summary = "Listar", description = "Endpoint para listar as tarefas paginadas.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Listado com sucesso.")
  })
  public ResponseEntity<Page<RetornoTarefaDTO>> listarTarefas(
      @RequestParam(defaultValue = "0") Integer pagina,
      @RequestParam(defaultValue = "10") Integer tamanhoPagina) {
    log.info("Listando tarefas");
    Page<RetornoTarefaDTO> tarefas = service.listarTarefas(pagina, tamanhoPagina);
    log.info("Tarefas listadas com sucesso: {}", tarefas);

    return ResponseEntity.ok().body(tarefas);
  }

  @PostMapping
  @Operation(summary = "Criar", description = "Endpoint para criar uma nova tarefa.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
      @ApiResponse(responseCode = "400", description = "Erro de validação.")
  })
  public ResponseEntity<RetornoTarefaDTO> criarTarefa(@RequestBody CriarTarefaDTO dto) {
    log.info("Criando tarefa");
    Tarefa tarefa = service.criarTarefa(dto);
    log.info("Tarefa criada com sucesso: {}", tarefa);

    RetornoTarefaDTO retornoTarefaDTO = RetornoTarefaDTO.fromEntity(tarefa);

    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(retornoTarefaDTO.id())
        .toUri();
    return ResponseEntity.created(uri).body(retornoTarefaDTO);
  }

  @DeleteMapping(value = "{id}")
  @Operation(summary = "Excluir", description = "Endpoint para excluir uma tarefa.")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Excluído com sucesso.")
  })
  public ResponseEntity<Void> excluirTarefa(@PathVariable Integer id) {
    log.info("Excluindo tarefa id: {}", id);
    service.excluirTarefa(id);
    log.info("Tarefa excluída com sucesso");
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "{id}")
  @Operation(summary = "Atualizar", description = "Endpoint para atualizar uma tarefa.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Atualizado com sucesso."),
      @ApiResponse(responseCode = "400", description = "Erro de validação."),
      @ApiResponse(responseCode = "404", description = "Não encontrado.")
  })
  public ResponseEntity<RetornoTarefaDTO> atualizarTarefa(@PathVariable Integer id, @Valid @RequestBody AtualizarTarefaDTO dto) {
    log.info("Atualizando tarefa id: {}", id);
    Tarefa tarefa = service.atualizarTarefa(id, dto);
    log.info("Tarefa atualizada com sucesso: {}", tarefa);

    RetornoTarefaDTO retornoTarefaDTO = RetornoTarefaDTO.fromEntity(tarefa);
    return ResponseEntity.ok().body(retornoTarefaDTO);
  }
}