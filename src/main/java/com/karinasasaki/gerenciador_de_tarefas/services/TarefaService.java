package com.karinasasaki.gerenciador_de_tarefas.services;

import com.karinasasaki.gerenciador_de_tarefas.entities.Tarefa;
import com.karinasasaki.gerenciador_de_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

  @Autowired
  private TarefaRepository repository;

  public List<Tarefa> listarTarefas() {
    return repository.findAll();
  }

  public Tarefa criarTarefa(Tarefa tarefa) {
    return repository.save(tarefa);
  }

  public void excluirTarefa(Integer id) {
    repository.deleteById(id);
  }

  public Tarefa atualizarTarefa(Integer id, Tarefa tarefaAtualizada) {
    Tarefa tarefa = repository.getReferenceById(id);
    atualizarCamposTarefa(tarefa, tarefaAtualizada);
    return repository.save(tarefa);
  }

  private void atualizarCamposTarefa(Tarefa tarefa, Tarefa tarefaAtualizada) {
    tarefa.setTitulo(tarefaAtualizada.getTitulo());
    tarefa.setDescricao(tarefaAtualizada.getDescricao());
    tarefa.setStatus(tarefaAtualizada.getStatus());
    tarefa.setDataConclusao(tarefaAtualizada.getDataConclusao());
  }
}