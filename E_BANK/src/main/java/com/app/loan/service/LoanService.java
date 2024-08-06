package com.app.loan.service;

import com.app.loan.dto.ApiResponse;

public interface LoanService {
	ApiResponse sanctionLoan(String request_id);
}
