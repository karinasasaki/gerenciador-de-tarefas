package io.github.karinasasaki.gerenciadordetarefas.controllers.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
    log.error("MethodArgumentNotValidException: {}", e.getMessage());

    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ErroResposta(status.value(), e.getFieldError().getDefaultMessage(), request.getRequestURI());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
    log.error("ConstraintViolationException: {}", e.getMessage());

    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ErroResposta(status.value(), e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList().get(0), request.getRequestURI());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErroResposta handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
    log.error("IllegalArgumentException: {}", e.getMessage());

    HttpStatus status = HttpStatus.NOT_FOUND;
    return new ErroResposta(status.value(), e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(InvalidFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleInvalidFormatException(InvalidFormatException e, HttpServletRequest request) {
    log.error("InvalidFormatException: {}", e.getMessage());

    HttpStatus status = HttpStatus.BAD_REQUEST;
    String campo = e.getPathReference().split("\"")[1];
    String mensagem = "O campo " + campo + " está em um formato inválido";
    return new ErroResposta(status.value(), mensagem, request.getRequestURI());
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleValidationException(ValidationException e, HttpServletRequest request) {
    log.error("ValidationException: {}", e.getMessage());

    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ErroResposta(status.value(), e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErroResposta handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
    log.error("HttpMessageNotReadableException: {}", e.getMessage());
    String mensagem = "Campo com formato inválido";

    HttpStatus status = HttpStatus.BAD_REQUEST;
    return new ErroResposta(status.value(), mensagem, request.getRequestURI());
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErroResposta handleErro500(RuntimeException e, HttpServletRequest request) {
    log.error("Erro 500: {}", e.getMessage());

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String mensagem = "Ocorreu um erro inesperado.";
    return new ErroResposta(status.value(), mensagem, request.getRequestURI());

  }
}