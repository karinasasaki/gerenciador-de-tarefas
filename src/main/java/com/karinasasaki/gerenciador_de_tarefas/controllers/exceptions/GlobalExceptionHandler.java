package com.karinasasaki.gerenciador_de_tarefas.controllers.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ErroResposta(status.value(), e.getFieldError().getDefaultMessage(), request.getRequestURI());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ErroResposta(status.value(), e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList().get(0), request.getRequestURI());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErroResposta handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    return new ErroResposta(status.value(), e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(InvalidFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleInvalidFormatException(InvalidFormatException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String campo = e.getPathReference().split("\"")[1];
    String mensagem = "O campo " + campo + " está em um formato inválido";
    return new ErroResposta(status.value(), mensagem, request.getRequestURI());
  }
}