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
}
