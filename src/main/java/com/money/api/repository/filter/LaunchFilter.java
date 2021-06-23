package com.money.api.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LaunchFilter {

  private String description;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDateIn;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDateUntil;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDueDateIn() {
    return dueDateIn;
  }

  public void setDueDateIn(LocalDate dueDateIn) {
    this.dueDateIn = dueDateIn;
  }

  public LocalDate getDueDateUntil() {
    return dueDateUntil;
  }

  public void setDueDateUntil(LocalDate dueDateUntil) {
    this.dueDateUntil = dueDateUntil;
  }
}
