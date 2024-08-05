package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AccountTypeDao;
import com.app.entity.account.AccountType;

@Service
@Transactional
public class AccountTypeSeviceImpl {
	
	@Autowired
	private AccountTypeDao accTypeDao;
	
	//ADD ACCOUNT Type
	
	
	
	
	
	//Fetching all AccountType 
	List<AccountType> getAllAccountType()
	{
		return null;
	}
	
	
	

}
