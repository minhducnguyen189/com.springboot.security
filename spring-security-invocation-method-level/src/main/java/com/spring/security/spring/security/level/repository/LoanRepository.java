package com.spring.security.spring.security.level.repository;

import com.spring.security.spring.security.level.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {

    List<LoanEntity> findByCustomerId(Integer customerId);

}
