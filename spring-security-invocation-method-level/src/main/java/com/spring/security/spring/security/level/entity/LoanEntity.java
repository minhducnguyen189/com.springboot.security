package com.spring.security.spring.security.level.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loan")
@Getter
@Setter
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date startDate;

    private String description;

    private float loan;

    private float paid;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

}
