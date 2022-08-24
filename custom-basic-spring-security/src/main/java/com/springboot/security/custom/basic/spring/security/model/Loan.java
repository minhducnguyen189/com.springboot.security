package com.springboot.security.custom.basic.spring.security.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Loan {

    private Integer id;
    private Date startDate;
    private String description;
    private float loan;
    private float paid;

}
