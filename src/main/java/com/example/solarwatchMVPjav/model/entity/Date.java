package com.example.solarwatchMVPjav.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Date {

  @Id
  @GeneratedValue
  private long id;

  private LocalDate date;

  private String sunrise;

  private String sunset;

  @ManyToOne()
  @JoinColumn()
  private City city;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getSunrise() {
    return sunrise;
  }

  public void setSunrise(String sunrise) {
    this.sunrise = sunrise;
  }

  public String getSunset() {
    return sunset;
  }

  public void setSunset(String sunset) {
    this.sunset = sunset;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
