package com.app.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BankDTO;
import com.app.loan.exceptions.ResourceNotFoundException;
import com.app.service.BankService;

@RestController
@RequestMapping("/bank")
@Validated
@CrossOrigin(origins = "http://localhost:3001/")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addBanks(@RequestBody @Valid BankDTO bank)
	{
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(bankService.addBank(bank));
		}catch(ResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(IllegalArgumentException  | HttpMessageNotReadableException  e) {
			return new ResponseEntity<>("Error reading arguments", HttpStatus.BAD_REQUEST);
		}catch(RuntimeException e) {
			return new ResponseEntity<>("Bank Already Exists !!!!", HttpStatus.BAD_REQUEST);
		}
	}
}
