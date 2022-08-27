package com.springboot.security.custom.basic.spring.security.repository;

import com.springboot.security.custom.basic.spring.security.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

}
