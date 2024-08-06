package com.app.loan.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan.dao.AccountDao;
import com.app.loan.entities.Account;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDao accDao;

	@Override
	public Account addAccount(Account acc) {
		// TODO Auto-generated method stub
		return accDao.save(acc);
	}

}
