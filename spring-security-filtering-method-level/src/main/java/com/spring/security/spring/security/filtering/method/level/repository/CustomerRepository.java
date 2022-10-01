package com.spring.security.spring.security.filtering.method.level.repository;

import com.spring.security.spring.security.filtering.method.level.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    List<CustomerEntity> findByEmail(String email);

}
