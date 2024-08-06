package com.app.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.loan.entities.Loan;
@Repository
public interface LoanDao extends JpaRepository<Loan, String>{

}
