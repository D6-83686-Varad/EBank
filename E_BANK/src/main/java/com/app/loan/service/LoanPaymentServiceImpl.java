package com.app.loan.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan.dao.LoanDao;
import com.app.loan.dao.LoanPaymentDao;
import com.app.loan.dto.ApiResponse;
import com.app.loan.entities.Loan;
import com.app.loan.entities.LoanPayment;
import com.app.loan.entities.TransactionStatus;
import com.app.loan.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class LoanPaymentServiceImpl implements LoanPaymentService{

	
	@Autowired
	private LoanDao loanDao;
	
	@Autowired
	private LoanPaymentDao loanPaymentDao;
	
	@Override
	public ApiResponse addPayment(String loan) {
		// TODO Auto-generated method stub
		System.out.println(loan);
		Loan loanDet = loanDao.findById(loan).orElseThrow();
		System.out.println(loanDet.getLoanAmount());
		if(loanDet.getRemainingAmount() != 0) {
			LoanPayment loanPayment= new LoanPayment(loanDet, (loanDet.getRemainingAmount()-loanDet.getEmi()), loanDet.getLoanAmount(), TransactionStatus.DEBIT);
			loanDet.addLoanPayment(loanPayment);
			loanDet.setRemainingAmount(loanDet.getRemainingAmount()-loanDet.getEmi());
			loanDao.save(loanDet);
			return new ApiResponse("Money Debited Successfully");
		}else {
			loanDet.setLoanStatus('C');
			return new ApiResponse("You have already paid your loan amount");
		}
	}

}
