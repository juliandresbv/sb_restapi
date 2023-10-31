package com.sb_restapi.adapter.controllers.person.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CreatePersonDto {
  private String name;

  private String email;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }
}
