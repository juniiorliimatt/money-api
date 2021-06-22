package com.money.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class MoneyExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource message;

  @ExceptionHandler({EmptyResultDataAccessException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
    var messageUser = message.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
    var messageDev = ex.toString();
    List<Erro> erros = Collections.singletonList(new Erro(messageUser, messageDev));
    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    var messageUser = message.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
    var messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
    List<Erro> erros = Collections.singletonList(new Erro(messageUser, messageDev));
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<Erro> erros = createListOfErros(ex.getBindingResult());
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  private List<Erro> createListOfErros(BindingResult result) {
    List<Erro> errors = new ArrayList<>();
    for (FieldError fe : result.getFieldErrors()) {
      var messageUser = message.getMessage(fe, LocaleContextHolder.getLocale());
      var messageDev = fe.toString();
      errors.add(new Erro(messageUser, messageDev));
    }
    return errors;
  }
}
