package com.karinasasaki.gerenciador_de_tarefas.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
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
}