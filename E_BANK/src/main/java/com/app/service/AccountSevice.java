package com.app.service;

import java.util.List;

import com.app.entity.account.Account;
import com.app.entity.account.AccountType;
import com.app.entity.bank.Bank;
import com.app.entity.customer.Customer;

public interface AccountSevice {
	
	Account addAccount(Customer customer, Bank bank, String accType);
	
	List<Account> getAllDeactivatedAccount();

}