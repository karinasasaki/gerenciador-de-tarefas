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

  public Tarefa findById(Long id) {
    return tarefaRepository.findById(id).get();
  }

  public Tarefa insert(Tarefa tarefa) {
    return tarefaRepository.save(tarefa);
  }
}