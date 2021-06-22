package com.money.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class CreatedEventResource extends ApplicationEvent {

  private final HttpServletResponse response;
  private final Long code;

  public CreatedEventResource(Object source, HttpServletResponse response, Long code) {
    super(source);
    this.response = response;
    this.code = code;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  public Long getCode() {
    return code;
  }
}
