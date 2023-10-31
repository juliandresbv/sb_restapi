package com.sb_restapi.application.use_cases.person;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.sb_restapi.adapter.exceptions.HttpException;
import com.sb_restapi.domain.models.person.PersonModel;
import com.sb_restapi.framework.db.entities.person.PersonEntity;
import com.sb_restapi.framework.db.entities.person.QPersonEntity;
import com.sb_restapi.adapter.controllers.person.dtos.CreatePersonDto;
import com.sb_restapi.adapter.controllers.person.dtos.UpdatePersonDto;
import com.sb_restapi.domain.repositories.person.interfaces.PersonRepository;
import com.sb_restapi.application.use_cases.person.interfaces.PersonUseCases;
import com.sb_restapi.adapter.controllers.person.responses.CreatePersonResponse;
import com.sb_restapi.adapter.controllers.person.responses.UpdatePersonResponse;

@Service
public class PersonUseCasesImpl implements PersonUseCases {
  private final PersonRepository personRepository;

  public PersonUseCasesImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public CreatePersonResponse createPerson(CreatePersonDto createPersonDto) throws Exception {
    QPersonEntity qPersonEntity = QPersonEntity.personEntity;
    boolean personExists = personRepository.exists(qPersonEntity.email.eq(createPersonDto.getEmail()));

    if (personExists) {
      throw new HttpException("Person already exists", HttpStatus.BAD_REQUEST);
    }

    PersonEntity createdPersonEntity = personRepository.save(
        new PersonEntity(
            createPersonDto.getName(),
            createPersonDto.getEmail(),
            createPersonDto.getBirthDate()));

    return new CreatePersonResponse(createdPersonEntity.getId());
  }

  @Override
  public List<PersonModel> getPersons() throws Exception {
    List<PersonEntity> personEntities = personRepository.findAll();

    return PersonEntity.toModelsList(personEntities);
  }

  @Override
  public PersonModel getPerson(String email) throws Exception {
    QPersonEntity qPersonEntity = QPersonEntity.personEntity;

    Optional<PersonEntity> foundPerson = personRepository.findOne(qPersonEntity.email.eq(email));

    if (foundPerson.isEmpty()) {
      throw new HttpException("Person not found", HttpStatus.BAD_REQUEST);
    }

    PersonEntity personEntity = foundPerson.get();

    return PersonEntity.toModel(personEntity);
  }

  @Override
  public UpdatePersonResponse updatePerson(String email, UpdatePersonDto updatePersonDto) throws Exception {
    QPersonEntity qPersonEntity = QPersonEntity.personEntity;

    if (updatePersonDto.getEmail().isPresent()) {
      String emailToUpdate = updatePersonDto.getEmail().get();

      if (email.equals(emailToUpdate) ||
          personRepository.exists(qPersonEntity.email.eq(emailToUpdate))) {
        throw new HttpException("Email already taken", HttpStatus.BAD_REQUEST);
      }
    }

    Optional<PersonEntity> personEntityToUpdate = personRepository.findOne(qPersonEntity.email.eq(email));

    if (personEntityToUpdate.isEmpty()) {
      throw new HttpException("Person not found", HttpStatus.BAD_REQUEST);
    }

    PersonEntity personEntity = personEntityToUpdate.get();

    updatePersonDto.getName().ifPresent(personEntity::setName);
    updatePersonDto.getEmail().ifPresent(personEntity::setEmail);
    updatePersonDto.getBirthDate().ifPresent(personEntity::setBirthDate);

    personEntity = personRepository.save(personEntity);

    return new UpdatePersonResponse(personEntity.getId());
  }

  @Override
  public void deletePerson(String email) throws Exception {
    QPersonEntity qPersonEntity = QPersonEntity.personEntity;

    Optional<PersonEntity> personEntityToDelete = personRepository.findOne(qPersonEntity.email.eq(email));

    if (personEntityToDelete.isEmpty()) {
      throw new HttpException("Person not found", HttpStatus.BAD_REQUEST);
    }

    PersonEntity personEntity = personEntityToDelete.get();

    personRepository.delete(personEntity);
  }

  @Override
  public List<String> getPersonsNames() throws Exception {
    return personRepository.getPersonsNames();
  }
}
