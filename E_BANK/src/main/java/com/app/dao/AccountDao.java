package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.account.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, String > {
	
}
