package com.app.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan.service.LoanPaymentService;
@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/loan/pay")
public class LoanPaymentController {
	
	@Autowired
	private LoanPaymentService loanPaymentService;
	
	
	@PostMapping("/{loanId}")
	public ResponseEntity<?> addLoanPayment(@PathVariable("loanId") String loanId){
		return ResponseEntity.ok(loanPaymentService.addPayment(loanId));
	}
}
