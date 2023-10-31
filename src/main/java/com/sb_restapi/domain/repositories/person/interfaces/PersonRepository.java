package com.sb_restapi.domain.repositories.person.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.sb_restapi.framework.db.entities.person.PersonEntity;

@Repository
public interface PersonRepository
    extends JpaRepository<PersonEntity, String>, QuerydslPredicateExecutor<PersonEntity>, PersonRepositoryExtension {
}
