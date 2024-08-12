package com.app.loan.service;

import java.time.LocalDate;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AccountDao;
import com.app.dao.BankDao;
import com.app.dao.TransactionHistoryDao;
import com.app.dto.AccountResponseDTO;
import com.app.dto.AccountTypeDTO;
import com.app.dto.TransactionHistoryDTO;
import com.app.loan.dao.CollateralDao;
import com.app.loan.dao.LoanDao;
import com.app.loan.dao.LoanDetailsDao;
import com.app.loan.dao.RequestDao;

import com.app.loan.dto.RequestDto;
import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.LoanDetailResponse;
import com.app.loan.entities.Collateral;
import com.app.loan.entities.Loan;
import com.app.loan.entities.LoanDetails;
import com.app.loan.entities.LoanPayment;
import com.app.loan.entities.Request;
import com.app.loan.entities.Status;
import com.app.loan.entities.TransactionStatus;
import com.app.loan.exceptions.ResourceNotFoundException;
import com.app.entity.account.Account;
import com.app.entity.bank.Bank;
import com.app.entity.payment.TransactionHistory;


import com.app.miscellaneous.mail.MailSend;
import com.app.miscellaneous.mail.RegistrationMailSender;


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
	private BankDao bankDao;
	
	@Autowired
	private LoanDetailsDao loanDetailsDao;
	
	@Autowired
	private TransactionHistoryDao transactionHistoryDao;

	@Override
	public ApiResponse addRequest(RequestDto reqDto) {
		
        // Retrieve the account and loan details based on provided IDs

		Account account = accDao.findById(reqDto.getAccountId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Account Id"));
		LoanDetails loanDetails = loanDetailsDao.findById(reqDto.getLoanType()).orElseThrow(()-> new ResourceNotFoundException("Invalid Loan details Id"));

		if(reqDto.getLoanAmount() > loanDetails.getMinAmount() && reqDto.getLoanAmount() < loanDetails.getMaxAmount() && reqDto.getLoanDuration() < loanDetails.getTenure()) {
        // Map RequestDto to Request entity and associate with account and loan details
			Request req = mapper.map(reqDto, Request.class);
			account.addRequest(req);
			loanDetails.addRequestLoanType(req);
			return new ApiResponse("Added a new Request");
		}else {
			return new ApiResponse("Please review the loan conditions. Please enter valid details");
		}
		
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
	public ApiResponse updateToPending(String requestId) {
		// TODO Auto-generated method stub
		Optional<Request> optionalEntity = reqDao.findById(requestId);
        if (optionalEntity.isPresent()) {
            Request entity = optionalEntity.get();
            if(entity.getStatus() == Status.R) {
            	entity.setStatus(Status.P);
            	reqDao.save(entity);
            	
            	RegistrationMailSender afterRequest = new RegistrationMailSender(entity.getAccount().getCustomer().getEmail(),entity.getAccount().getAccountNo(), entity.getRequestId(), entity.getAccount().getBank().getBankName());
        		MailSend.sendEmail(afterRequest.getMessage(), afterRequest.getSubject(), afterRequest.getTo());
            	
        		return new ApiResponse("Succeesfully Updated to Pending");
            }else {
            	if(entity.getStatus() == Status.A) {
            		return new ApiResponse("Loan for given request is already Approved !");
            	}else if(entity.getStatus() == Status.D){
            		return new ApiResponse("Loan for given request is Declined !");
            	}else {
            		return new ApiResponse("Given Loan request is already marked as Pending !");
            	}
            }
        } else {
            // Handle the case where the entity is not found
            throw new RuntimeException("Entity not found with id: " + requestId);
        }
	}

	@Override
	public ApiResponse updateToApproved(String requestId) {
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
              	Loan forLoanId = loanDao.save(loan);
              	forLoanId.addLoanPayment(new LoanPayment(forLoanId, ((entity.getLoanAmount()+interest)/entity.getLoanDuration()), (entity.getLoanAmount()+interest), TransactionStatus.CREDIT));
                

              	//Fund transfer to account
              	account.setBalance(account.getBalance()+entity.getLoanAmount());
                TransactionHistory transactionHistory  = new TransactionHistory();
                account.addTransaction(entity, loanDetails,transactionHistory);
                
                //Bank fund management
              	Bank bank = account.getBank();
              	bank.subtractFundAvailable(entity.getLoanAmount());
              	bank.addLoanDisbursed(entity.getLoanAmount());
              	bank.addLoanExpected(entity.getLoanAmount()+interest);
              	
              	bankDao.save(bank);
              	accDao.save(account);
              	transactionHistoryDao.save(transactionHistory);
              	
            	RegistrationMailSender afterApproved = new RegistrationMailSender(account.getCustomer().getEmail(), account.getAccountNo(), entity.getRequestId(), account.getBank().getBankName(), forLoanId.getLoanNo());
        		MailSend.sendEmail(afterApproved.getMessage(), afterApproved.getSubject(), afterApproved.getTo());

              	
             	return new ApiResponse("Your Loan Request is approved , You will recieve money in short time.....");
            }else {
            	if(entity.getStatus() == Status.A) {
            		return new ApiResponse("Loan for given request is already Approved !");
            	}else if(entity.getStatus() == Status.D){
            		return new ApiResponse("Loan for given request is Declined !");
            	}else {
            		return new ApiResponse("Given Loan request is marked as Requested !");
            	}
            }
            
        } else {
            // Handle the case where the entity is not found
            throw new RuntimeException("Entity not found with id: " + requestId);
        }
	}



	@Override
	public ApiResponse updateToDeclined(String requestId) {
		// TODO Auto-generated method stub
		Optional<Request> optionalEntity = reqDao.findById(requestId);
        if (optionalEntity.isPresent()) {
            Request entity = optionalEntity.get();
            if(entity.getStatus()==Status.P | entity.getStatus() == Status.R) {
            	entity.setStatus(Status.D);
            	reqDao.save(entity);
            	
            	RegistrationMailSender afterDeclined = new RegistrationMailSender(entity.getAccount().getCustomer().getEmail(), entity.getRequestId(), entity.getAccount().getBank().getBankName());
        		MailSend.sendEmail(afterDeclined.getMessage(), afterDeclined.getSubject(), afterDeclined.getTo());
            	
        		return new ApiResponse("Request is declined..  ");
            }else {
            	if(entity.getStatus() == Status.A) {
            		return new ApiResponse("Can't delete approved requests");
            	}else {
            		return new ApiResponse("Given request is already marked as declined");
            	}
            }
        } else {
            // Handle the case where the entity is not found
            throw new RuntimeException("Entity not found with id: " + requestId);
        }
	}

	
	   

	@Override
	public List<LoanDetailResponse> getListOfLoansByAcccount(String accountNo) {
		 Optional<Account> account = accDao.findById(accountNo);
		    if (account.isPresent()) {
		        Account account2 = account.get();
		        List<Loan> loans = account2.getLoan();

		        if (loans.isEmpty()) {
		            return List.of(); // Return an empty list instead of null
		        } else {
		            // Assuming you have a ModelMapper instance configured
		            ModelMapper modelMapper = new ModelMapper();
		            return loans.stream()
		                        .map(loan -> modelMapper.map(loan, LoanDetailResponse.class))
		                        .collect(Collectors.toList());
		        }
		    } else {
		        return List.of(); // Return an empty list instead of null
		    }
		}
	}

	


