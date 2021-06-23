package com.money.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "launch")
public class Launch implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long code;

  @NotNull
  private String description;

  @NotNull
  @Column(name = "due_date")
  private LocalDate dueDate;

  @Column(name = "payment_date")
  private LocalDate paymentDate;

  @NotNull
  @Column(name = "value_launch")
  private BigDecimal valueLaunch;

  private String observation;

  @NotNull
  @Enumerated(EnumType.STRING)
  private LaunchType type;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "code_category")
  private Category category;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "code_person")
  private Person person;

  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDate getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
  }

  public BigDecimal getValueLaunch() {
    return valueLaunch;
  }

  public void setValueLaunch(BigDecimal valueLaunch) {
    this.valueLaunch = valueLaunch;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public LaunchType getType() {
    return type;
  }

  public void setType(LaunchType type) {
    this.type = type;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var launch = (Launch) o;
    return Objects.equals(code, launch.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
