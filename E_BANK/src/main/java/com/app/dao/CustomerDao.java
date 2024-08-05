package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.customer.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>{

	@Query("SELECT c FROM Customer c WHERE c.status = false")
    java.util.List<Customer> findByStatusFalse();


}
