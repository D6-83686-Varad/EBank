package com.app.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.ResourceNotFoundException;
import com.app.loan.dto.LoanResponseDto;
import com.app.loan.entities.Loan;
import com.app.loan.service.LoanPaymentService;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/loan/pay")
public class LoanPaymentController {
	
	@Autowired
	private LoanPaymentService loanPaymentService;
	
	
	@PostMapping("/{loanId}")
	public ResponseEntity<?> addLoanPayment(@PathVariable("loanId") String loanId){
		return ResponseEntity.ok(loanPaymentService.addPayment(loanId));
	}
	
	@GetMapping("/{accountNo}")
	    public ResponseEntity<?> getLoanDetailsByAccountNo(@PathVariable String accountNo) {
	        try {
	            LoanResponseDto loan = loanPaymentService.getLoanDetailsByAccountNo(accountNo);
	            return ResponseEntity.ok(loan);
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	        }
	    }
}
