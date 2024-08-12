package com.app.loan.service;

import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.LoanResponseDto;

public interface LoanPaymentService {

	ApiResponse addPayment(String loan);
	LoanResponseDto getLoanDetailsByAccountNo(String accountNo);
	
}
