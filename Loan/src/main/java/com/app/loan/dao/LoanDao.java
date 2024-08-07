package com.app.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.loan.entities.Loan;

public interface LoanDao extends JpaRepository<Loan, String>{

}
