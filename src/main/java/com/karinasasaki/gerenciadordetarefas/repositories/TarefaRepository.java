package com.karinasasaki.gerenciadordetarefas.repositories;

import com.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}