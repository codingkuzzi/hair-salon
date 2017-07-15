package com.epicodus.ak.hairsalon.model;

import java.time.LocalDate;

public class Client {
  private int id;
  private String lastName;
  private String firstName;
  private String gender;
  private LocalDate dateOfBirth;
  private Integer stylistId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Integer getStylistId() {
    return stylistId;
  }

  public void setStylistId(Integer stylistId) {
    this.stylistId = stylistId;
  }
}
