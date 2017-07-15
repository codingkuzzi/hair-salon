package com.epicodus.ak.hairsalon.model;

import java.time.LocalDate;

public class Appointment {
  private int id;
  private int clientId;
  private int stylistId;
  private LocalDate dateAndTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public int getStylistId() {
    return stylistId;
  }

  public void setStylistId(int stylistId) {
    this.stylistId = stylistId;
  }

  public LocalDate getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(LocalDate dateAndTime) {
    this.dateAndTime = dateAndTime;
  }
}
