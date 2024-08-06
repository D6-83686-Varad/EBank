package com.app.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan.dto.CollateralDto;
import com.app.loan.service.CollateralService;

@RestController
@RequestMapping("/loan/collateral")
public class LoanCollateralController {
	@Autowired
	private CollateralService collService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addCollateralRequest(@RequestBody CollateralDto collateral){
		return ResponseEntity.status(HttpStatus.CREATED).body(collService.addCollateral(collateral));
	}
	
	//  Once customer adds collateral admin will review the collateral details and trigger a method to create a new entry using Request Table and Collateral Table as Loan table  
}
