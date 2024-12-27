package io.github.karinasasaki.gerenciadordetarefas.services;

import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.AtualizarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.CriarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import io.github.karinasasaki.gerenciadordetarefas.entities.enums.StatusTarefa;
import io.github.karinasasaki.gerenciadordetarefas.repositories.TarefaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Slf4j
@Service
public class TarefaService {

  private final TarefaRepository repository;

  TarefaService(TarefaRepository repository) {
    this.repository = repository;
  }

  public Page<Tarefa> listarTarefas(Integer pagina, Integer tamanhoPagina) {
    Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
    return repository.findAll(pageable);
  }

  public Tarefa criarTarefa(CriarTarefaDTO dto) {
    log.debug("Dados da tarefa inseridos: {}", dto);

    Tarefa tarefa = dto.mapParaTarefaEntidade();
    tarefa.anularDescricaoEmBranco();
    tarefa.dataConclusaoDeveSerMaiorQueDataCriacao();

    return repository.save(tarefa);
  }

  public void excluirTarefa(Integer id) {
    repository.deleteById(id);
  }

  public Tarefa atualizarTarefa(Integer id, AtualizarTarefaDTO dto) {
    log.debug("Dados da tarefa inseridos: {}", dto);

    Optional<Tarefa> tarefaOptional = repository.findById(id);

    if (tarefaOptional.isEmpty()) {
      throw new IllegalArgumentException("Tarefa não encontrada");
    }

    Tarefa tarefa = tarefaOptional.get();

    if (dto.titulo() != null && !dto.titulo().isBlank()) {
      tarefa.setTitulo(dto.titulo());
    }

    tarefa.setDescricao(dto.descricao());

    if (dto.status() != null) {
      tarefa.setStatus(StatusTarefa.getStatus(dto.status()));
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
    tarefa.setDataConclusao(Instant.from(formatter.parse(dto.dataConclusao())));

    tarefa.anularDescricaoEmBranco();
    tarefa.dataConclusaoDeveSerMaiorQueDataCriacao();

    return repository.save(tarefa);
  }
}