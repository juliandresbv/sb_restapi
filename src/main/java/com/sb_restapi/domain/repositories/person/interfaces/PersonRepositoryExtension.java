package com.sb_restapi.domain.repositories.person.interfaces;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface PersonRepositoryExtension {
  public List<String> getPersonsNames() throws Exception;
}
