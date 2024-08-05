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
public class AccountTypeSeviceImpl implements AccountSevice{
	
	@Autowired
	private AccountTypeDao accTypeDao;
	
	@Autowired
	private ModelMapper mapper;
	
	//ADD ACCOUNT Type
	
	ApiResponse addAccountType(AccountTypeDTO accTypeDto)
	{
		AccountType accType = mapper.map(accTypeDto, AccountType.class);
		if(accTypeDao.existsById(accType.getAccTypeId()))
		{
			return new ApiResponse("Already account Type is present");
		}
		accTypeDao.save(accType);
		return new ApiResponse("Account Type added succesfully"); 
	}
	
	//Get accountType
		AccountTypeDTO getAccountType(String accType)
		{
			AccountType acctype= accTypeDao.findByAccTypeName(accType).orElseThrow(()->new ResourceNotFoundException("Invalid account Type"));
			
			return mapper.map(acctype, AccountTypeDTO.class);
		}
	
	// Update accountType
		
		String updateAccountType(String accType,float interestRate)
		{
			AccountType acctype= accTypeDao.findByAccTypeName(accType).orElseThrow(()->new ResourceNotFoundException("Invalid account Type"));
			acctype.setInterestRate(interestRate);
			accTypeDao.save(acctype);
			return "AccountType updated Succesully";
		}
	
	
	//Fetching all AccountType 
	List<AccountType> getAllAccountType()
	{
		return accTypeDao.findAll();
	}
	
	
	

}
