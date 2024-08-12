package com.app.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerReturnDTO;
import com.app.entity.customer.Customer;
import com.app.entity.enums.Role;
import com.app.exceptions.BadRequestException;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/bank/customers")
@Validated
public class SuperAdminController {

	@Autowired
    private CustomerService customerService;
	
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
}
