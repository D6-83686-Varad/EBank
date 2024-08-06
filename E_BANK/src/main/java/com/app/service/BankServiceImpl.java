package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BankDao;
import com.app.dto.BankDTO;
import com.app.entity.bank.Bank;


@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankdao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public String addBank(BankDTO bank) {
		// TODO Auto-generated method stub
		Bank bankN= mapper.map(bank, Bank.class);
		bankdao.save(bankN);
		
		return "Bank added succesfully";
	}

}
