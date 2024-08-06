//package com.app.loan.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.app.loan.dao.AccountDao;
//import com.app.loan.dao.CollateralDao;
//import com.app.loan.dao.LoanDao;
//import com.app.loan.dao.RequestDao;
//import com.app.loan.dto.ApiResponse;
//import com.app.loan.entities.Account;
//import com.app.loan.entities.Loan;
//import com.app.loan.entities.Request;
//import com.app.loan.exceptions.ResourceNotFoundException;
//
//public class LoanServiceImpl implements LoanService{
//
//	@Autowired
//	private LoanDao loanDao;
//	
//	@Autowired
//	private RequestDao requestDao;
//	
//	@Autowired 
//	private AccountDao accDao;
//	
//	@Autowired
//	private CollateralDao collaDao;
//	@Override
//	public ApiResponse sanctionLoan(String request_id) {
//		// TODO Auto-generated method stub
//		Request request = requestDao.findById(request_id).orElseThrow(()->new ResourceNotFoundException("No request for current id found"));
//		Loan loan= new Loan(loan.setAccount_id(accDao.findById(request_id).orElseThrow(()-> new ResourceNotFoundException("No account founfd with given request id")));
//		request.getAccount().getAccountNo();
//		request.getCollaterals().getFirst().getCollateralNo();
//		return null;
//	}
//	
//}


