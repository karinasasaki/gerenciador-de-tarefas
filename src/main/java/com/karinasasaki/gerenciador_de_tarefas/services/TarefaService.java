package com.karinasasaki.gerenciador_de_tarefas.services;

import com.karinasasaki.gerenciador_de_tarefas.controllers.dtos.AtualizarTarefaDTO;
import com.karinasasaki.gerenciador_de_tarefas.controllers.dtos.CriarTarefaDTO;
import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.entities.enums.StatusTarefa;
import com.karinasasaki.gerenciador_de_tarefas.repositories.TarefaRepository;
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
    return repository.save(tarefa);
  }
}