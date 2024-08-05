package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerDao;
import com.app.entity.customer.Customer;

@Service
@Transactional
public class CustomerServiceImpl {
@Autowired
private CustomerDao customerDao;

 public String addCustomer() {
		 return null;
 }
 
 
 
}
