package com.money.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

  private String longboat;
  private String number;
  private String complement;
  private String neighborhood;
  private String zipCode;
  private String city;
  private String estate;

  public Address() {
  }

  public Address(String longboat, String number, String complement, String neighborhood, String zipCode, String city, String estate) {
    this.longboat = longboat;
    this.number = number;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.zipCode = zipCode;
    this.city = city;
    this.estate = estate;
  }

  public String getLongboat() {
    return longboat;
  }

  public void setLongboat(String longboat) {
    this.longboat = longboat;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getEstate() {
    return estate;
  }

  public void setEstate(String estate) {
    this.estate = estate;
  }

}
