package com.app.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan.dto.LoanDetailsDto;
import com.app.loan.service.LoanDetailsService;

@RestController
@RequestMapping("/loantypes")
public class LoanDetailsController {
	
	@Autowired
	private LoanDetailsService loDeService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addLoanTypeDetails(@RequestBody LoanDetailsDto loDeDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(loDeService.addNewLoanDetails(loDeDto));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> removeLoanType(@PathVariable("id") String id){
		return ResponseEntity.status(HttpStatus.CREATED).body(loDeService.removeLoanDretails(id));
	}
	
}
