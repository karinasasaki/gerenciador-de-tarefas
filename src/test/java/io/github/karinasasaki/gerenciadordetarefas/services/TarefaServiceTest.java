package io.github.karinasasaki.gerenciadordetarefas.services;

import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.AtualizarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.controllers.dtos.CriarTarefaDTO;
import io.github.karinasasaki.gerenciadordetarefas.entities.Tarefa;
import io.github.karinasasaki.gerenciadordetarefas.repositories.TarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

  @Mock
  private TarefaRepository repository;

  @InjectMocks
  private TarefaService service;

  @Test
  void TarefaService_ListarTarefas_DeveRetornarListaVazia_QuandoNaoTiverValor() {
    Page<Tarefa> tarefas = new PageImpl<>(new ArrayList<>());

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(tarefas);

    Page<Tarefa> listaVazia = service.listarTarefas(0, 10);

    Assertions.assertNotNull(listaVazia);
    Assertions.assertFalse(listaVazia.hasContent());
  }

  @Test
  void TarefaService_ListarTarefas_DeveRetornarDuasTarefas_QuandoTiverDoisValores() {
    Tarefa tarefa1 = Tarefa.builder().build(),
        tarefa2 = Tarefa.builder().build();
    List<Tarefa> listaTarefas = new ArrayList<>(List.of(tarefa1, tarefa2));
    Page<Tarefa> tarefas = new PageImpl<>(listaTarefas);

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(tarefas);

    Page<Tarefa> duasTarefas = service.listarTarefas(0, 10);

    Assertions.assertNotNull(duasTarefas);
    Assertions.assertEquals(2, duasTarefas.getTotalElements());
  }

  @Test
  void TarefaService_CriarTarefa_DeveRetornarTarefaCriada() {
    Tarefa retornoEsperado = Tarefa.builder()
        .id(1)
        .titulo("Criar cartão")
        .descricao("Ajustar cor do botão")
        .status("PENDENTE")
        .dataConclusao(null).build();

    Mockito.when(repository.save(Mockito.any(Tarefa.class))).thenReturn(retornoEsperado);

    CriarTarefaDTO dto = CriarTarefaDTO.builder()
        .titulo("Criar cartão")
        .descricao("Ajustar cor do botão")
        .dataConclusao(null).build();

    Tarefa tarefaCriada = service.criarTarefa(dto);

    Assertions.assertNotNull(tarefaCriada);
    Assertions.assertEquals(retornoEsperado, tarefaCriada);
  }

  @Test
  void TarefaService_AtualizarTarefa_DeveRetornarTarefaAtualizada() {
    Tarefa retornoEsperado = Tarefa.builder()
        .id(1)
        .titulo("Avaliar PR")
        .descricao(null)
        .status("EM_ANDAMENTO")
        .dataConclusao(null).build();

    Tarefa tarefaDesatualizada = Tarefa.builder()
        .id(1)
        .titulo("Avaliar PR")
        .descricao(null)
        .status("PENDENTE")
        .dataConclusao(null).build();

    Mockito.when(repository.findById(1)).thenReturn(Optional.ofNullable(tarefaDesatualizada));
    Mockito.when(repository.save(Mockito.any(Tarefa.class))).thenReturn(retornoEsperado);

    AtualizarTarefaDTO dto = AtualizarTarefaDTO.builder()
        .status("EM_ANDAMENTO").build();

    Tarefa tarefaAtualizada = service.atualizarTarefa(1, dto);

    Assertions.assertNotNull(tarefaAtualizada);
    Assertions.assertEquals(retornoEsperado, tarefaAtualizada);
  }

  @Test
  void TarefaService_AtualizarTarefaComIdInexistente_DeveRetornarIllegalArgumentException() {
    Mockito.when(repository.findById(0)).thenReturn(Optional.empty());

    AtualizarTarefaDTO dto = AtualizarTarefaDTO.builder()
        .status("EM_ANDAMENTO").build();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.atualizarTarefa(0, dto);
    });
  }
}