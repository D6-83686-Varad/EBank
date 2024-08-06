package com.app.service;

import java.util.List;

import com.app.entity.account.Account;
import com.app.entity.account.AccountType;
import com.app.entity.bank.Bank;
import com.app.entity.customer.Customer;
import com.app.entity.enums.AccountStatus;

public interface AccountSevice {
	
	Account addAccount(Customer customer, Bank bank, String accType);
	
	List<Account> getAllDeactivatedAccount();
	
	List<Account> getAllActivatedAccount();
	
	List<Account> getAllSuspendedAccount();
	
	String changeStatusOfSuspendedAccount(String accId);
	
	String changeStatusOfDeactivatedAccount(String accId);

	

	String changeStatusOfActivatedAccount(String accId);

}