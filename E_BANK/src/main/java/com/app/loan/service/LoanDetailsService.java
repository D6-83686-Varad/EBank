package com.app.loan.service;

import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.LoanDetailsDto;

public interface LoanDetailsService {
	ApiResponse addNewLoanDetails(LoanDetailsDto loanDetails);
	ApiResponse removeLoanDretails(String loanTypeName);
}
