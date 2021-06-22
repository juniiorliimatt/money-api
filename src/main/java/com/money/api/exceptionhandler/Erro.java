package com.money.api.exceptionhandler;

public class Erro {
  private final String messageUser;
  private final String messageDev;

  public Erro(String messageUser, String messageDev) {
    this.messageUser = messageUser;
    this.messageDev = messageDev;
  }

  public String getMessageUser() {
    return messageUser;
  }

  public String getMessageDev() {
    return messageDev;
  }
}
