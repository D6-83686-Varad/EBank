package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.DefaultEditorKit.CutAction;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerDao;
import com.app.dto.CustomerDTO;
import com.app.entity.customer.Customer;
import com.app.exceptions.ResourceNotFoundException;



@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
@Autowired
private CustomerDao customerDao;
@Autowired
private ModelMapper mapper ;
@Override
public String addCustomer (CustomerDTO customerDto) {
	Customer customer = mapper.map(customerDto, Customer.class) ;
	customerDao.save(customer);
	return "Customer Added Successfully..!";
}

@Override
public String updateCustomerEmailAndPhone(Long customerId, String newEmail, String newPhoneNumber) {
    Customer existingCustomer = customerDao.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
    if (newEmail != null && !newEmail.isEmpty()) {
        existingCustomer.setEmail(newEmail);
    }
    if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
        existingCustomer.setPhoneNumber(newPhoneNumber);
    }
    customerDao.save(existingCustomer);
    return "Customer Details Updated Successfully!";
}

@Override
public CustomerDTO getCustomer(Long customerId) {
    Customer customer = customerDao.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
    return mapper.map(customer, CustomerDTO.class);
}
@Override
public List<CustomerDTO> getCustomersWithStatusFalse() {
    List<Customer> customers = customerDao.findByStatusFalse();
    return customers.stream()
                    .map(customer -> mapper.map(customer, CustomerDTO.class))
                    .collect(Collectors.toList());
}
@Override
public String setCustomerStatusToTrue(Long customerId) {
    Customer customer = customerDao.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
    
    customer.setStatus(true);
    customerDao.save(customer);
    return "Customer status updated to true!";
}

}
