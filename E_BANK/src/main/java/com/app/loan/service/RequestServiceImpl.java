package com.app.loan.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.app.loan.exceptions.ResourceNotFoundException;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.app.loan.dao.AccountDao;
import com.app.loan.dao.CollateralDao;
import com.app.loan.dao.LoanDao;
import com.app.loan.dao.LoanDetailsDao;
import com.app.loan.dao.RequestDao;
import com.app.loan.dto.RequestDto;
import com.app.loan.entities.Account;
import com.app.loan.entities.Collateral;
import com.app.loan.entities.Loan;
import com.app.loan.entities.LoanDetails;
import com.app.loan.entities.Request;
import com.app.loan.entities.Status;
import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.CollateralDto;

@Service
@Transactional
public class RequestServiceImpl implements RequestService{

	@Autowired
	private RequestDao reqDao;
	
	@Autowired
	private AccountDao accDao;
	
	@Autowired
	private LoanDao loanDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CollateralDao collateralDao;
	
	@Autowired
	private LoanDetailsDao loanDetailsDao;

	@Override
	public ApiResponse addRequest(RequestDto reqDto) {
		System.out.println("In Service");
		Account account = accDao.findById(reqDto.getAccountId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Account Id"));
		LoanDetails loanDetails = loanDetailsDao.findById(reqDto.getLoanType()).orElseThrow(()-> new ResourceNotFoundException("Invalid Loan details Id"));
		System.out.println("in account");
		Request req = mapper.map(reqDto, Request.class);
		account.addRequest(req);
		loanDetails.addRequestLoanType(req);
		return new ApiResponse("Added a new Req.uest");
		
	}

	@Override
	public List<Request> viewPending() {
		// TODO Auto-generated method stub
		return reqDao.findAllByStatusWithPending();
	}
	
	@Override
	public List<Request> viewRequested() {
		// TODO Auto-generated method stub
		System.out.println("Hii ok");
		return reqDao.findAllByStatusWithRequested();
	}

	@Override
	public List<Request> viewApproved() {
		// TODO Auto-generated method stub
		return reqDao.findAllByStatusWithApproved();
	}

	@Override
	public List<Request> viewDeclined() {
		// TODO Auto-generated method stub
		return reqDao.findAllByStatusWithDeclined();
	}

	@Override
	public String updateToPending(String requestId) {
		// TODO Auto-generated method stub
		Optional<Request> optionalEntity = reqDao.findById(requestId);
        if (optionalEntity.isPresent()) {
            Request entity = optionalEntity.get();
            entity.setStatus(Status.P);
            reqDao.save(entity);
            return "Succeed";
        } else {
            // Handle the case where the entity is not found
            throw new RuntimeException("Entity not found with id: " + requestId);
        }
	}

	@Override
	public String updateToApproved(String requestId) {
		// TODO Auto-generated method stub
		System.out.println("ok");
		Optional<Request> optionalEntity = reqDao.findById(requestId);
//		System.out.println(optionalEntity.toString());
		System.out.println("Hii");
        if (optionalEntity.isPresent()) {
        	
        	System.out.println("hiii");
            Request entity = optionalEntity.get();
            if(entity.getStatus()  == Status.P) {
            	System.out.println("okkkkkkk");
//              System.out.println(entity.getLoanAmount());
            	entity.setStatus(Status.A);
            	Request savedRequest = entity;
            	reqDao.save(entity);
            	System.out.println(entity.getRequestId());
            	System.out.println("CO");
            	Collateral collateral = collateralDao.findByRequest(entity);
              	System.out.println("Acc");
//              entity.getAccount().getAccountNo()
              	Account account = accDao.findById(entity.getAccount().getAccountNo()).orElseThrow(()-> new ResourceNotFoundException("Account Not found with given request id"));
              	System.out.println("Loan");
              	LoanDetails loanDetails = loanDetailsDao.findById(entity.getDetails().getLoanName()).orElseThrow(()-> new ResourceNotFoundException("Loan details Found "));
              	Loan loan = new Loan(account, entity.getLoanAmount(), (entity.getLoanAmount()/entity.getLoanDuration()), LocalDate.now(), LocalDate.now().plusMonths(entity.getLoanDuration()), loanDetails, collateral );
              	loanDao.save(loan);
              	return "Succeed";
            }else {
            	return "Your Loan is already Approved";
            }
            
        } else {
            // Handle the case where the entity is not found
            throw new RuntimeException("Entity not found with id: " + requestId);
        }
	}

	@Override
	public String updateToDeclined(String requestId) {
		// TODO Auto-generated method stub
		Optional<Request> optionalEntity = reqDao.findById(requestId);
        if (optionalEntity.isPresent()) {
            Request entity = optionalEntity.get();
            entity.setStatus(Status.D);
            reqDao.save(entity);
            return "Succeed";
        } else {
            // Handle the case where the entity is not found
            throw new RuntimeException("Entity not found with id: " + requestId);
        }
	}

}
