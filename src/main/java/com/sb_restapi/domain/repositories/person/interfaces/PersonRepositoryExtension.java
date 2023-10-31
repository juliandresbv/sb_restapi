package com.sb_restapi.domain.repositories.person.interfaces;

import java.util.List;

public interface PersonRepositoryExtension {
  public List<String> getPersonsNames() throws Exception;
}
