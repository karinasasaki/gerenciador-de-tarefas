package com.karinasasaki.gerenciadordetarefas.services;

import com.karinasasaki.gerenciadordetarefas.controllers.dtos.AtualizarTarefaDTO;
import com.karinasasaki.gerenciadordetarefas.controllers.dtos.CriarTarefaDTO;
import com.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import com.karinasasaki.gerenciadordetarefas.entities.enums.StatusTarefa;
import com.karinasasaki.gerenciadordetarefas.repositories.TarefaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository repository;

  public Page<Tarefa> listarTarefas(Integer pagina, Integer tamanhoPagina) {
    Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
    return repository.findAll(pageable);
  }

  public Tarefa criarTarefa(CriarTarefaDTO dto) {
    Tarefa tarefa = dto.mapParaTarefaEntidade();

    anularDescricaoEmBranco(tarefa);
    dataConclusaoDeveSerMaiorQueDataCriacao(tarefa);
    return repository.save(tarefa);
  }

  public void excluirTarefa(Integer id) {
    repository.deleteById(id);
  }

  public Tarefa atualizarTarefa(Integer id, AtualizarTarefaDTO dto) {
    Optional<Tarefa> tarefaOptional = repository.findById(id);

    if (tarefaOptional.isEmpty()) {
      throw new IllegalArgumentException("Tarefa não encontrada");
    }

    Tarefa tarefa = tarefaOptional.get();
    if (dto.titulo() != null) {
      tarefa.setTitulo(dto.titulo());
    }
    tarefa.setDescricao(dto.descricao());
    if (dto.status() != null) {
      tarefa.setStatus(StatusTarefa.getStatus(dto.status()));
    }
    tarefa.setDataConclusao(dto.dataConclusao());

    anularDescricaoEmBranco(tarefa);
    dataConclusaoDeveSerMaiorQueDataCriacao(tarefa);
    return repository.save(tarefa);
  }

  private void anularDescricaoEmBranco(Tarefa tarefa) {
    if (tarefa.getDescricao() != null && tarefa.getDescricao().isBlank()) {
      tarefa.setDescricao(null);
    }
  }

  private void dataConclusaoDeveSerMaiorQueDataCriacao(Tarefa tarefa) {
    if (tarefa.getDataConclusao() != null && tarefa.getDataConclusao().before(tarefa.getDataCriacao())) {
      throw new ValidationException("A dataConclusao deve ser posterior a dataCriacao");
    }
  }
}