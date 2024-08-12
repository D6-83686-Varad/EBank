package com.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AccountTypeDTO;
import com.app.dto.ApiResponse;
import com.app.entity.account.AccountType;
import com.app.response.dto.AccountTypeAllDetailsDto;
import com.app.service.AccountTypeService;

@RestController
@RequestMapping("/accType")
@Validated
public class AccountTypeController {
	
	@Autowired
	private AccountTypeService accService;
	@Autowired
	private ModelMapper mapper;

//	@Autowired(required=true)
//	private AccountTypeService accService;
	
	@GetMapping
	public ResponseEntity<?> getAllAccountType()
	{
		try {
			List <AccountType> accountTypes = accService.getAllAccountType();
			if(accountTypes.isEmpty()) {
				return new ResponseEntity<>("No Account Types available", HttpStatus.NOT_FOUND);
			}else {
				List<AccountTypeDTO> accTypeDto = accountTypes.stream().map(accountType->mapper.map(accountType, AccountTypeDTO.class)).collect(Collectors.toList());
				return new ResponseEntity<>(accTypeDto, HttpStatus.ACCEPTED);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/{accType}")
	public ResponseEntity<?> getAccountType(@PathVariable("accType")String accType)
	{	
		try {
			AccountTypeAllDetailsDto accTypeAllDetails = accService.getAccountType(accType);
			return new ResponseEntity<>(accTypeAllDetails, HttpStatus.ACCEPTED);
		}catch(Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addAccountType(@RequestBody @Valid AccountTypeDTO accDTO)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(accService.addAccType(accDTO));
        
	}
	@PatchMapping("{accType}/update")
	public ResponseEntity<?> updateAccountType(@PathVariable("accType")String accType,@RequestBody @Valid float interestRate)
	{
		return ResponseEntity.ok(accService.updateAccountType(accType, interestRate));
        
	}
}
