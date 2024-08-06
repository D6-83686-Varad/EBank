package com.app.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.loan.entities.Account;
import com.app.loan.entities.Collateral;
import com.app.loan.entities.Request;

@Repository
public interface AccountDao extends JpaRepository<Account, String>{
	
	@Query("SELECT u FROM Account u WHERE u.request = :request")
    Account findAccountByRequestId(@Param("request") String request);
	
}
