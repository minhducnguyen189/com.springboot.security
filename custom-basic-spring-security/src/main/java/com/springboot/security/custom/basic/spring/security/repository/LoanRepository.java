package com.springboot.security.custom.basic.spring.security.repository;

import com.springboot.security.custom.basic.spring.security.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {

    List<LoanEntity> findByCustomerId(Integer customerId);

}
