package com.sec.ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/myBLoans")
    public String getLoanDetails(String input){
        return "Here are the Loan details from DB";
    }
}