package com.sb_restapi.framework.db.entities.person;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import org.hibernate.annotations.UuidGenerator;

import com.sb_restapi.domain.models.person.PersonModel;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Entity
@Table(name = "persons")
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
  @Id
  @Getter
  @Setter
  @UuidGenerator
  private String id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String email;

  @Getter
  @Setter
  private LocalDate birthDate;

  public PersonEntity(String name, String email, LocalDate birthDate) {
    this.name = name;
    this.email = email;
    this.birthDate = birthDate;
  }

  public static PersonModel toModel(PersonEntity entity) {
    return new PersonModel(
        entity.id,
        entity.name,
        entity.email,
        entity.birthDate);
  }

  public static List<PersonModel> toModelsList(List<PersonEntity> entities) {
    return entities
        .stream()
        .map(PersonEntity::toModel)
        .collect(Collectors.toList());
  }
}
