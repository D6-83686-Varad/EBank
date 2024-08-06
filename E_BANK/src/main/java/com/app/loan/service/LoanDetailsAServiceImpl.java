package com.app.loan.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan.dao.LoanDetailsDao;
import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.LoanDetailsDto;
import com.app.loan.entities.LoanDetails;

@Service
@Transactional
public class LoanDetailsAServiceImpl implements LoanDetailsService{

	@Autowired
	private LoanDetailsDao loTyDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addNewLoanDetails(LoanDetailsDto loanDetails) {
		// TODO Auto-generated method stub
		if(loTyDao.existsById(loanDetails.getLoanName())) {
			return new ApiResponse("Given type already present in table");
		}else {
			LoanDetails loanDetailsC = mapper.map(loanDetails, LoanDetails.class);
			loTyDao.save(loanDetailsC);
			return new ApiResponse("LoanDetails Created");
		}
		
	}

	@Override
	public ApiResponse removeLoanDretails(String loanTypeName) {
		
		if(loTyDao.existsById(loanTypeName)) {
			loTyDao.deleteById(loanTypeName);
			return new ApiResponse("Deleted Successfully");
		}else {
			
			return new ApiResponse("LoanDetails is not present");
		}
	}


}
