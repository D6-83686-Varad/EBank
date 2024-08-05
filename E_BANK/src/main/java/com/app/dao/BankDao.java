package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.bank.Bank;

@Repository
public interface BankDao extends JpaRepository<Bank,String > {
	
}
