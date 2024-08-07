package com.app.loan.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.app.loan.exceptions.ResourceNotFoundException;


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
		
        // Retrieve the account and loan details based on provided IDs
		Account account = accDao.findById(reqDto.getAccountId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Account Id"));
		LoanDetails loanDetails = loanDetailsDao.findById(reqDto.getLoanType()).orElseThrow(()-> new ResourceNotFoundException("Invalid Loan details Id"));

        // Map RequestDto to Request entity and associate with account and loan details
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
		Optional<Request> optionalEntity = reqDao.findById(requestId);

        if (optionalEntity.isPresent()) {
        	
            Request entity = optionalEntity.get();
            if(entity.getStatus()  == Status.P) {
            	
            	entity.setStatus(Status.A);
            	reqDao.save(entity);
            	
                // Retrieve collateral, account, and loan details
            	Collateral collateral = collateralDao.findByRequest(entity);
              	Account account = accDao.findById(entity.getAccount().getAccountNo()).orElseThrow(()-> new ResourceNotFoundException("Account Not found with given request id"));
              	LoanDetails loanDetails = loanDetailsDao.findById(entity.getDetails().getLoanName()).orElseThrow(()-> new ResourceNotFoundException("Loan details Found "));
              	
                // Calculate interest and create a new loan
              	float interest = entity.getLoanAmount()*entity.getLoanDuration()*(entity.getDetails().getInterestRate()/100);
              	Loan loan = new Loan(account, (entity.getLoanAmount()+interest), ((entity.getLoanAmount()+interest)/entity.getLoanDuration()), LocalDate.now(), LocalDate.now().plusMonths(entity.getLoanDuration()), loanDetails, collateral );
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
