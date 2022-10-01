package com.spring.security.spring.security.level.controller;

import com.spring.security.spring.security.level.model.Loan;
import com.spring.security.spring.security.level.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/v1/loan")
    public ResponseEntity<String> getLoanDetail() {
        return ResponseEntity.ok("This is the loan details");
    }

    @PostAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/v1/loan/customers/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomerId(@PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(this.loanService.getLoans(customerId));
    }

}