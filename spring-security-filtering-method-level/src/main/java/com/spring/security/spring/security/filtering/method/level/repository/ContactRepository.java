package com.spring.security.spring.security.filtering.method.level.repository;

import com.spring.security.spring.security.filtering.method.level.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

}
