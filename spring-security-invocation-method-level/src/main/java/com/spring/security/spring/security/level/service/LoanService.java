package com.spring.security.spring.security.level.service;

import com.spring.security.spring.security.level.entity.LoanEntity;
import com.spring.security.spring.security.level.model.Loan;
import com.spring.security.spring.security.level.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getLoans(Integer customerId) {
        List<LoanEntity> loanEntities = this.loanRepository.findByCustomerId(customerId);
        List<Loan> loans = new ArrayList<>();
        for (LoanEntity loanEntity: loanEntities) {
            Loan loan = new Loan();
            loan.setId(loanEntity.getId());
            loan.setLoan(loanEntity.getLoan());
            loan.setPaid(loanEntity.getPaid());
            loan.setDescription(loanEntity.getDescription());
            loan.setStartDate(loanEntity.getStartDate());
            loans.add(loan);
        }
        return loans;

    }

}
