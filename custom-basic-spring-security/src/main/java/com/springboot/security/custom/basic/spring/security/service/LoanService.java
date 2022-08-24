package com.springboot.security.custom.basic.spring.security.service;

import com.springboot.security.custom.basic.spring.security.entity.LoanEntity;
import com.springboot.security.custom.basic.spring.security.model.Loan;
import com.springboot.security.custom.basic.spring.security.repository.LoanRepository;
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
