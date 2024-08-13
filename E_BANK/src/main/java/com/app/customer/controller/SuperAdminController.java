package com.app.customer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerReturnDTO;
import com.app.entity.customer.Customer;
import com.app.entity.enums.Role;
import com.app.exceptions.BadRequestException;
import com.app.service.BankService;
import com.app.service.CustomerService;
@CrossOrigin(origins ="http://localhost:3000/")
@RestController
@RequestMapping("/superAdmin")
@Validated
public class SuperAdminController {

	@Autowired
    private CustomerService customerService;
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/admins")
    public ResponseEntity<List<CustomerReturnDTO>> getAllAdmins() {
        List<CustomerReturnDTO> admins = customerService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
	
	@PutMapping("/updateAdmin/{id}")
    public ResponseEntity<String> createAdmin(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomer(id);
        if (Role.ROLE_SUPER_ADMIN.equals(customer.getRole())) {
            throw new BadRequestException("Cannot update role for a super admin");
        }
        String msg = customerService.createAdmin(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @PutMapping("/deleteAdmin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomer(id);
        if (Role.ROLE_ADMIN.equals(customer.getRole())) {
        	String msg = customerService.deleteAdmin(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        throw new BadRequestException("Not Found");
    }
    
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerReturnDTO>> getAllCustomers() {
        List<CustomerReturnDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    @GetMapping("/getAllCustomersOnly")
    public ResponseEntity<List<CustomerReturnDTO>> getAllCustomersOnly() {
        List<CustomerReturnDTO> customers = customerService.getAllCustomersOnly();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    @GetMapping("/getAllCustomersDisabled")
    public ResponseEntity<List<CustomerReturnDTO>> getAllDisabled() {
        List<CustomerReturnDTO> customers = customerService.getAllDisabled();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    @GetMapping("/getBankDetails")
	public ResponseEntity<?> getBank(){
		return ResponseEntity.status(HttpStatus.OK).body(bankService.getBankDetails());
	}
}
