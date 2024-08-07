package com.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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


	@Override
	public Account addAccount(Customer customer, Bank bank, String accType) {
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
		Account accountCreated = accDao.save(account);
		customerDao.save(customer);
		
	
		
		return accountCreated;
	}


	@Override
	public List<Account> getAllDeactivatedAccount() {
		List<Account> accList = accDao.getAllDeactivatedAccount();
		if(accList.isEmpty())
		{
			throw new ResourceNotFoundException("There are no Deactivated Accounts");
		}
		return accList;
	}
	


	@Override
	public List<Account> getAllActivatedAccount() {
		List<Account> accList = accDao.getAllActivatedAccount();
		if(accList.isEmpty())
		{
			throw new ResourceNotFoundException("There are no Deactivated Accounts");
		}
		return accList;
	}
	@Override
	 public void depositToAccount(String accountId, double amount) {
	        if (amount <= 0) {
	            throw new IllegalArgumentException("Deposit amount must be positive.");
	        }
	        
	        Account account = accDao.findById(accountId)
	                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
	        
	        double newBalance = account.getBalance() + amount;
	        account.setBalance(newBalance);
	        accDao.save(account);
	    }
	@Override
	    public void withdrawFromAccount(String accountId, double amount) {
	        if (amount <= 0) {
	            throw new IllegalArgumentException("Withdrawal amount must be positive.");
	        }
	        
	        Account account = accDao.findById(accountId)
	                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
	        
	        if (account.getBalance() < amount) {
	            throw new IllegalArgumentException("Insufficient balance.");
	        }
	        
	        double newBalance = account.getBalance() - amount;
	        account.setBalance(newBalance);
	        accDao.save(account);
	    }
	@Override
	public double checkBalance(String accountId) {
        Account account = accDao.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
        
        return account.getBalance();
    }
	
	@Override
	public List<Account> getAllSuspendedAccount() {
		List<Account> accList = accDao.getAllSuspendedAccount();
		if(accList.isEmpty())
		{
			throw new ResourceNotFoundException("There are no Deactivated Accounts");
		}
		return accList;
	}
	
	


	@Override
	public String changeStatusOfSuspendedAccount(String accId) {
		Account account = accDao.findById(accId).orElseThrow(()-> new ResourceNotFoundException("Status is not valid"));
		if(account.getStatus()==AccountStatus.SUSPENDED) {
			
			account.setStatus(AccountStatus.ACTIVATED);
			accDao.save(account);
			return "Updated account";
		}
		return "Failed to update";
		
	}
	@Override
	public String changeStatusOfDeactivatedAccount(String accId) {
		Account account = accDao.findById(accId).orElseThrow(()-> new ResourceNotFoundException("Status is not valid"));
		if(account.getStatus()==AccountStatus.DEACTIVATED) {
			account.setStatus(AccountStatus.ACTIVATED);
			accDao.save(account);
			return "Updated account";
		}
		return "Failed";
		
	}

	
	@Override
	public String changeStatusOfActivatedAccount(String accId) {
		Account account = accDao.findById(accId).orElseThrow(()-> new ResourceNotFoundException("Status is not valid"));
		if(account.getStatus()==AccountStatus.ACTIVATED) {
			
			account.setStatus(AccountStatus.DEACTIVATED);
			accDao.save(account);
			return "Updated account";
		}
		return "Failed to update";
		
	}


	@Override
	public boolean checkAccountSuspension(Account account) {
		LocalDateTime updatedOn = account.getUpdatedOn();
        LocalDateTime now = LocalDateTime.now();
        
        long daysBetween = ChronoUnit.DAYS.between(updatedOn, now);
        //TODO change the time , 1 day just for checking
        if (daysBetween > 180) {
            account.setStatus(AccountStatus.SUSPENDED);
            accDao.save(account);
            return false;
        }
        
        return true;
	}
}
