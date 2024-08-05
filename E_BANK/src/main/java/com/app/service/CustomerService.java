package com.app.service;

import com.app.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerDTO customerDto);

    String updateCustomerEmailAndPhone(Long customerId, String newEmail, String newPhoneNumber);

    CustomerDTO getCustomer(Long customerId);

    List<CustomerDTO> getCustomersWithStatusFalse();

    String setCustomerStatusToTrue(Long customerId);
}
