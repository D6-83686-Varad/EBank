package com.app.service;

import com.app.dto.BankDTO;
import com.app.entity.bank.Bank;

public interface BankService {
	
	String addBank(BankDTO bank);
	
	Bank getBankDetails();

}
