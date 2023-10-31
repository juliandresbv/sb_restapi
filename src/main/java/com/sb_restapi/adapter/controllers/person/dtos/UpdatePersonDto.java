package com.sb_restapi.adapter.controllers.person.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Optional;
import java.time.LocalDate;

public class UpdatePersonDto {
  private Optional<String> name = Optional.empty();

  private Optional<String> email = Optional.empty();

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Optional<LocalDate> birthDate = Optional.empty();

  public Optional<String> getName() {
    return name;
  }

  public Optional<String> getEmail() {
    return email;
  }

  public Optional<LocalDate> getBirthDate() {
    return birthDate;
  }
}
