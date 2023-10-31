package com.sb_restapi.framework.db.repositories.person;

import com.querydsl.jpa.JPQLQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import com.sb_restapi.framework.db.entities.person.PersonEntity;
import com.sb_restapi.framework.db.entities.person.QPersonEntity;
import com.sb_restapi.domain.repositories.person.interfaces.PersonRepositoryExtension;

@Repository
public class PersonRepositoryImpl extends QuerydslRepositorySupport implements PersonRepositoryExtension {
  @Autowired
  @PersistenceContext
  private EntityManager entityManager;

  public PersonRepositoryImpl() {
    super(PersonEntity.class);
  }

  @Override
  public List<String> getPersonsNames() throws Exception {
    QPersonEntity qPersonEntity = QPersonEntity.personEntity;
    JPQLQuery<PersonEntity> query = from(qPersonEntity);

    return query
        .select(qPersonEntity.name)
        .fetch();
  }
}
