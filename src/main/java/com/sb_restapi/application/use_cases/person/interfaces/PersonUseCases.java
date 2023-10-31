package com.sb_restapi.application.use_cases.person.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sb_restapi.adapter.controllers.person.dtos.CreatePersonDto;
import com.sb_restapi.adapter.controllers.person.dtos.UpdatePersonDto;
import com.sb_restapi.adapter.controllers.person.responses.CreatePersonResponse;
import com.sb_restapi.adapter.controllers.person.responses.UpdatePersonResponse;
import com.sb_restapi.domain.models.person.PersonModel;

@Component
public interface PersonUseCases {
  public CreatePersonResponse createPerson(CreatePersonDto createPersonDto) throws Exception;

  public List<PersonModel> getPersons() throws Exception;

  public PersonModel getPerson(String email) throws Exception;

  public UpdatePersonResponse updatePerson(String email, UpdatePersonDto updatePersonDto) throws Exception;

  public void deletePerson(String email) throws Exception;

  public List<String> getPersonsNames() throws Exception;
}
