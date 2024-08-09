package com.app.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.loan.entities.Collateral;
import com.app.loan.entities.Loan;
import com.app.loan.entities.Request;


public interface LoanDao extends JpaRepository<Loan, String>{
	@Query("SELECT c FROM Collateral c WHERE c.request = :request")
    Collateral findByRequest(@Param("request") Request request);
	
	@Query("SELECT l FROM Loan l WHERE l.accountId =:accountId")
	List<Loan> findLoanByAccountId(@Param("accountId") String accountId);
}
