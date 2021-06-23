package com.money.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long code;

  @NotNull
  @Size(min = 3, max = 255)
  private String name;

  @Embedded
  private Address address;

  @NotNull
  @Column(name = "is_active")
  private Boolean isActive;

  @JsonIgnore
  @Transient
  public boolean isInativo() {
    return !this.isActive;
  }

  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var person = (Person) o;
    return getCode().equals(person.getCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCode());
  }
}
