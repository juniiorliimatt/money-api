package com.money.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MoneyExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource message;

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    var messageUser = message.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
    var messageDev = ex.getCause();
    return handleExceptionInternal(ex, new Error(messageUser, messageDev), headers,
      HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return handleExceptionInternal(ex, createListOfErros(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
  }

  private List<Erro> createListOfErros(BindingResult result) {
    List<Erro> errors = new ArrayList<>();

    for (FieldError fe : result.getFieldErrors()) {
      String messageUser = message.getMessage(fe, LocaleContextHolder.getLocale());
      String messageDev = fe.toString();
      errors.add(new Erro(messageUser, messageDev));
    }

    return errors;
  }
}

class Erro {
  private String messageUser;
  private String messageDev;

  public Erro(String messageUser) {
    this.messageUser = messageUser;
  }

  public Erro(String messageUser, String messageDev) {
    this.messageUser = messageUser;
    this.messageDev = messageDev;
  }

  public String getMessageUser() {
    return messageUser;
  }

  public void setMessageUser(String messageUser) {
    this.messageUser = messageUser;
  }

  public String getMessageDev() {
    return messageDev;
  }

  public void setMessageDev(String messageDev) {
    this.messageDev = messageDev;
  }
}
