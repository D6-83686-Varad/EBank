package com.app.service;

import com.app.dto.BankDTO;
import com.app.entity.bank.Bank;

public interface BankService {
	
	String addBank(BankDTO bank);
	
	Bank getBankDetails();

	boolean addFundAvailable(double fundAvailable);
	
	boolean subtractFundAvailable(double fundAvailable);
	
	boolean addFundReceived(double fundReceived);
	
	boolean subtractFundReceived(double fundReceived);
	
	boolean addFundToPay(double fundToPay);
	
	boolean subtractFundToPay(double fundToPay);
	
	boolean addLoanDisbursed(double loanDisbursed);
	
	boolean subtractLoanDisbursed(double loanDisbursed);
	
	boolean addLoanRecovered(double loanRecovered);
	
	boolean subtractLoanRecovered(double loanRecovered);
	
	boolean addLoanExpected(double loanExpected);
	
	boolean subtractLoanExpected(double loanExpected);

}
