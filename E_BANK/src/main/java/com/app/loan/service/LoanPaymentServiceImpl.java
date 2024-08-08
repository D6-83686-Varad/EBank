package com.app.loan.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan.dao.LoanDao;
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
	
	@Override
	public ApiResponse addPayment(String loan) {
		// TODO Auto-generated method stub
		Loan loanDet = loanDao.findById(loan).orElseThrow(()-> new ResourceNotFoundException("Loan Details Not Found for given Id"+ loan));
		if(loanDet.getRemainingAmount() > 0) {
			LoanPayment loanPayment= new LoanPayment(loanDet, (loanDet.getRemainingAmount()-loanDet.getEmi()), loanDet.getLoanAmount(), TransactionStatus.DEBIT);
			loanDet.addLoanPayment(loanPayment);
			loanDet.setRemainingAmount(loanDet.getRemainingAmount()-loanDet.getEmi());
			loanDao.save(loanDet);
			return new ApiResponse("Money Debited Successfully");
		}else {
			if(loanDet.getRemainingAmount() == 0) {
				loanDet.setLoanStatus('C');
				return new ApiResponse("You have already paid your loan amount");
			}else {
				return new ApiResponse("Some issue remaining amount cannot be negative");
			}
		}
	}

}
