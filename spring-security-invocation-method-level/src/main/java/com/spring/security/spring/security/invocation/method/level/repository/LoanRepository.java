package com.spring.security.spring.security.invocation.method.level.repository;

import com.spring.security.spring.security.invocation.method.level.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {

    List<LoanEntity> findByCustomerId(Integer customerId);

}
