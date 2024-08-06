package com.app.service;

import javax.servlet.Registration;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AccountDao;
import com.app.dao.AccountTypeDao;
import com.app.dao.BankDao;
import com.app.dao.CustomerDao;
import com.app.entity.account.Account;
import com.app.entity.account.AccountType;
import com.app.entity.bank.Bank;
import com.app.entity.customer.Customer;
import com.app.entity.enums.AccountStatus;
import com.app.entity.enums.Role;
import com.app.exceptions.ResourceNotFoundException;
import com.app.miscellaneous.mail.MailSend;
import com.app.miscellaneous.mail.RegistrationMailSender;

@Service
@Transactional
public class AccountSeviceImpl implements AccountSevice{
	@Autowired
	private AccountDao accDao;
	
	@Autowired
	private BankDao bankDao;
	@Autowired
	private AccountTypeDao accTyDao;
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private Account account;
	@Autowired
	private AccountType acctype;

	@Override
	public String addAccount(Customer customer, Bank bank, String accType) {
		// TODO Auto-generated method stub
		AccountType accTypes =accTyDao.findByAccTypeName(accType).orElseThrow(()->new ResourceNotFoundException("Hi"));
		Account account =new Account();
		//account.setAccType(accTypes);
		account.setBank(bank);
		account.setCustomer(customer);
		account.setStatus(AccountStatus.ACTIVATED);
		accTypes.linkAccount(account);
		customer.addAccountToCustomer(account);
		//customer.setRole(Role.ROLE_CUSTOMER);
		accDao.save(account);
		customerDao.save(customer);
		//String accountNo,double balance,String accTypeId,long customerId
		RegistrationMailSender reg =new RegistrationMailSender(customer.getEmail(),account.getAccountNo(),account.getBalance(),acctype.getAccTypeId(),customer.getCustomerId());
		//MailSend mail =new MailSend();
		MailSend.sendEmail(reg.getMessage(), reg.getSubject(), reg.getTo(), reg.getFrom());
	
		
		return "Succesfully Added";
	}

}
