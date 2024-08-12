package com.app.customer.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AccountTypeDTO;
import com.app.dto.ApiResponse;
import com.app.dto.CustomerDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.entity.account.AccountType;
import com.app.entity.customer.Customer;
import com.app.exceptions.InvalidTpinException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.response.dto.AccountTypeAllDetailsDto;
import com.app.service.AccountSevice;
import com.app.service.AccountTypeService;
import com.app.service.CustomerService;
@CrossOrigin(origins ="http://localhost:3000/")
@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {
	
	@Autowired
	private AccountTypeService accTypeService;
	
	@Autowired
	private AccountSevice accService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/types")
	public ResponseEntity<?> getAllAccountType()
	{
		try {
			List <AccountType> accountTypes = accTypeService.getAllAccountType();
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
	
	
	@GetMapping("/type/{accType}")
	public ResponseEntity<?> getAccountType(@PathVariable("accType")String accType)
	{
		try {
			AccountTypeAllDetailsDto accTypeAllDetails = accTypeService.getAccountType(accType);
			return new ResponseEntity<>(accTypeAllDetails, HttpStatus.ACCEPTED);
		}catch(Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/balance/{accId}")
	public ResponseEntity<?> getBalance(@PathVariable("accId")String accId)
	{
		return ResponseEntity.ok(accService.checkBalance(accId));
				
	}
	
	@PostMapping("/verify-tpin/{customerId}")
	 public ResponseEntity<String> verifyTpin(@PathVariable Long customerId, @RequestParam String tpin) {
	        try {
	            customerService.verifyCustomerTpin(customerId, tpin);
	            return new ResponseEntity<>("TPIN verified successfully", HttpStatus.OK);
	        } catch (ResourceNotFoundException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	        } catch (InvalidTpinException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	
	  
	 
	    @PostMapping
	    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDTO customerDto) {
	        if (customerDto.getFirstName() != null) {
	            customerDto.setFirstName(customerDto.getFirstName().toUpperCase());
	        }
	        if (customerDto.getMiddleName() != null) {
	            customerDto.setMiddleName(customerDto.getMiddleName().toUpperCase());
	        }
	        if (customerDto.getLastName() != null) {
	            customerDto.setLastName(customerDto.getLastName().toUpperCase());
	        }
	        String response = customerService.addCustomer(customerDto);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }


	    @PutMapping("/{id}")
	    public ResponseEntity<String> updateCustomerEmailAndPhone(
	            @PathVariable Long id,
	            @RequestParam(required = false) @Email(message = "Invalid email format") String email,
	            @RequestParam(required = false) @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits") String phoneNumber) {
	        String response = customerService.updateCustomerEmailAndPhone(id, email, phoneNumber);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    @PutMapping("/updatePassword/{id}")
	    public ResponseEntity<String> updateCustomerPassword(
	            @PathVariable Long id,
	            @RequestParam @NotNull(message = "Password must not be empty") String password){
	           String response =  customerService.updateCustomerPassword(id , password);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
	        Customer customer = customerService.getCustomer(id);
	        return new ResponseEntity<>(customer, HttpStatus.OK);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
	      LoginResponseDTO loginResponse = customerService.login(loginRequest.getEmailOrPhone(), loginRequest.getPassword());
	        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	    }
	    

	    @PostMapping("/{customerId}/update-tpin")
	    public ResponseEntity<String> updateTpin(@PathVariable Long customerId, @RequestParam @Min(100000) @Max(999999) String newTpin) {
	        try {
	            customerService.updateCustomerTpin(customerId, newTpin);
	            return new ResponseEntity<>("TPIN updated successfully", HttpStatus.OK);
	        } catch (ResourceNotFoundException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	
}
