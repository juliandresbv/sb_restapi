package com.sb_restapi.adapter.controllers.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.sb_restapi.domain.models.person.PersonModel;
import com.sb_restapi.adapter.controllers.person.dtos.CreatePersonDto;
import com.sb_restapi.adapter.controllers.person.dtos.UpdatePersonDto;
import com.sb_restapi.application.use_cases.person.interfaces.PersonUseCases;
import com.sb_restapi.adapter.controllers.person.responses.CreatePersonResponse;
import com.sb_restapi.adapter.controllers.person.responses.UpdatePersonResponse;

@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonUseCases personUseCases;

  public PersonController(@Qualifier("PersonUseCasesImpl") PersonUseCases personUseCases) {
    this.personUseCases = personUseCases;
  }

  @PostMapping
  public CreatePersonResponse createPerson(@RequestBody CreatePersonDto createPersonDto) throws Exception {
    return this.personUseCases.createPerson(createPersonDto);
  }

  @GetMapping
  public List<PersonModel> getPersons() throws Exception {
    return this.personUseCases.getPersons();
  }

  @GetMapping("/{email}")
  public PersonModel getPerson(@PathVariable String email) throws Exception {
    return this.personUseCases.getPerson(email);
  }

  @PutMapping("/{email}")
  public UpdatePersonResponse updatePerson(@PathVariable String email, @RequestBody UpdatePersonDto updatePersonDto)
      throws Exception {
    return this.personUseCases.updatePerson(email, updatePersonDto);
  }

  @DeleteMapping("/{email}")
  public void deletePerson(@PathVariable String email) throws Exception {
    this.personUseCases.deletePerson(email);
  }

  @GetMapping("/names")
  public List<String> getPersonsNames() throws Exception {
    return this.personUseCases.getPersonsNames();
  }
}
