package com.sb_restapi.domain.models.person;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

import com.sb_restapi.framework.db.entities.person.PersonEntity;

@Getter
@Setter
@AllArgsConstructor
public class PersonModel {
  private String id;

  private String name;

  private String email;

  private LocalDate birthDate;

  public PersonModel(String name, String email, LocalDate birthDate) {
    this.name = name;
    this.email = email;
    this.birthDate = birthDate;
  }

  public static PersonEntity toEntity(PersonModel model) {
    return new PersonEntity(
        model.id,
        model.name,
        model.email,
        model.birthDate);
  }

  public static List<PersonEntity> toEntitiesList(List<PersonModel> models) {
    return models
        .stream()
        .map(PersonModel::toEntity)
        .collect(Collectors.toList());
  }
}
