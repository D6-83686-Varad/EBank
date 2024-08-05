package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.customer.Customer;
@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>{

}
