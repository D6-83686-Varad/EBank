package com.app.loan.service;

import com.app.loan.entities.Account;


/**
 * Service interface for handling operations related to `Account` entities.
 * This interface defines the methods available for interacting with account data.
 */
public interface AccountService {
	
	
	/**
     * Adds a new account to the system.
     * This method is responsible for creating and persisting a new `Account` entity in the database.
     *
     * @param acc The `Account` entity to be added. It should contain all necessary details required for account creation.
     * @return The created `Account` entity, including any generated values or additional information.
     */
	Account addAccount(Account acc);
}
