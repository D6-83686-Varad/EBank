package com.app.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan.entities.Account;
import com.app.loan.service.AccountService;

@RestController
@RequestMapping("/loan/account")
public class LoanAccountController {
	@Autowired
	private AccountService accService;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addAccounts(@RequestBody Account account)
	{
		
		return ResponseEntity.status(HttpStatus.CREATED).body(accService.addAccount(account));
	}
}
