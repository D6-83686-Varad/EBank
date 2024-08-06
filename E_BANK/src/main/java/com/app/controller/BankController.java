package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BankDTO;
import com.app.service.BankService;

@RestController
@RequestMapping("/bank")
@Validated
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addBanks(@RequestBody @Valid BankDTO bank)
	{
		System.out.println(bank.getBankName());
		return ResponseEntity.status(HttpStatus.CREATED).body(bankService.addBank(bank));
	}

}
