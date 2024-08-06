package com.app.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.loan.entities.Collateral;
import com.app.loan.entities.LoanDetails;
@Repository
public interface LoanDetailsDao extends JpaRepository<LoanDetails, String>{
	@Query("SELECT u FROM LoanDetails u WHERE u.loanName = :requestId")
    LoanDetails findLoanTypeByRequestId(@Param("requestId") String username);
}
