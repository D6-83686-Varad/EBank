package com.app.loan.service;

import com.app.loan.dto.ApiResponse;

public interface LoanPaymentService {

	ApiResponse addPayment(String loan);
	
}
