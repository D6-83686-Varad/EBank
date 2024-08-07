package com.app.loan.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan.dao.AccountDao;
import com.app.loan.entities.Account;


/**
 * Implementation of the `AccountService` interface.
 * This class provides the concrete implementation of the service methods defined in the `AccountService` interface.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
    // DAO for accessing account data from the database
	@Autowired
	private AccountDao accDao;

	
	/**
     * Adds a new account to the system.
     * This method saves the provided `Account` entity to the database using the `AccountDao`.
     *
     * @param acc The `Account` entity to be added. It should contain all necessary details for account creation.
     * @return The saved `Account` entity, which may include generated values or additional information from the database.
     */
	@Override
	public Account addAccount(Account acc) {
		
        // Save the account entity using the DAO and return the saved entity
		return accDao.save(acc);
	}

}
