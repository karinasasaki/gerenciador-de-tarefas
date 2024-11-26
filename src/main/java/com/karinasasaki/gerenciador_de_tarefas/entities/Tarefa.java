package com.karinasasaki.gerenciador_de_tarefas.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String descricao;
  private String status = "PENDENTE";
  private Calendar dataCriacao = Calendar.getInstance();
  private Calendar dataConclusao;

  public Tarefa() {}

  public Tarefa(String titulo, String descricao) {
    this.titulo = titulo;
    this.descricao = descricao;
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Calendar getDataCriacao() {
    return dataCriacao;
  }

  public Calendar getDataConclusao() {
    return dataConclusao;
  }

  public void setDataConclusao(Calendar dataConclusao) {
    this.dataConclusao = dataConclusao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tarefa tarefa = (Tarefa) o;
    return Objects.equals(id, tarefa.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}