package com.app.service;

import com.app.dto.CustomerDTO;
import com.app.entity.customer.Customer;

import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerDTO customerDto);

    String updateCustomerEmailAndPhone(Long customerId, String newEmail, String newPhoneNumber);

    Customer getCustomer(Long customerId);

    List<Customer> getCustomersWithStatusFalse();

    String setCustomerStatusToTrue(Long customerId);

	Customer login(String emailOrPhone, String password);

	String createAdmin(Long id);

	List<Customer> getAllCustomers();

	String deleteAdmin(Long customerId);

	List<Customer> getAllAdmins();

	void verifyCustomerTpin(Long customerId, int inputTpin);

	void updateCustomerTpin(Long customerId, int newTpin);

}
