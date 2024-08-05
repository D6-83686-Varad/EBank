package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.dto.CustomerDTO;
import com.app.entity.customer.Customer;
import com.app.service.CustomerService;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import java.util.List;

@RestController
@RequestMapping("/bank/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

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

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/status/false")
    public ResponseEntity<List<Customer>> getCustomersWithStatusFalse() {
        List<Customer> customers = customerService.getCustomersWithStatusFalse();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> setCustomerStatusToTrue(@PathVariable Long id) {
        String response = customerService.setCustomerStatusToTrue(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
