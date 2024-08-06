package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AccountTypeDao;
import com.app.dto.AccountTypeDTO;
import com.app.dto.ApiResponse;
import com.app.entity.account.AccountType;
import com.app.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class AccountTypeSeviceImpl implements AccountTypeService{
	
	@Autowired
	private AccountTypeDao accTypeDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public AccountTypeDTO getAccountType(String acc) {
		// TODO Auto-generated method stub
		AccountType accType=accTypeDao.findByAccTypeName(acc).orElseThrow(()->new ResourceNotFoundException("no such account type"));
		
		
		return mapper.map(accType, AccountTypeDTO.class);
	}

	@Override
	public List<AccountType> getAllAccountType() {
		// TODO Auto-generated method stub
		return accTypeDao.findAll();
	}

	@Override
	public String updateAccountType(String acc, float interestRate) {
		// TODO Auto-generated method stub
		if(accTypeDao.existsByAccTypeName(acc))
		{
			AccountType accType=accTypeDao.findByAccTypeName(acc).orElseThrow(()->new ResourceNotFoundException("no such account type"));
			accType.setInterestRate(interestRate);
			accTypeDao.save(accType);
			return "AccountType updated";
			
		}
		
		return "failed to update";

		
	}

	@Override
	public ApiResponse addAccType(AccountTypeDTO acc) {
		// TODO Auto-generated method stub
		AccountType accType =mapper.map(acc, AccountType.class);
		accTypeDao.save(accType);
		return new ApiResponse("Account added sucessfully");
	}
	
	

	
	

}
