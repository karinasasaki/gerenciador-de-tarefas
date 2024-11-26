package com.karinasasaki.gerenciador_de_tarefas.services;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository tarefaRepository;

  public List<Tarefa> findAll() {
    return tarefaRepository.findAll();
  }

  public Tarefa insert(Tarefa tarefa) {
    return tarefaRepository.save(tarefa);
  }

  public Tarefa update(Long id, Tarefa tarefaAtualizada) {
    Tarefa tarefa = tarefaRepository.getReferenceById(id);
    atualizarTarefa(tarefa, tarefaAtualizada);
    return tarefaRepository.save(tarefa);
  }

  private void atualizarTarefa(Tarefa tarefa, Tarefa tarefaAtualizada) {
    tarefa.setTitulo(tarefaAtualizada.getTitulo());
    tarefa.setDescricao(tarefaAtualizada.getDescricao());
    tarefa.setStatus(tarefaAtualizada.getStatus());
    tarefa.setDataConclusao(tarefaAtualizada.getDataConclusao());
  }
}