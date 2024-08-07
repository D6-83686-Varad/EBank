package com.app.service;

import javax.transaction.Transactional;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AccountDao;
import com.app.dao.BankDao;
import com.app.dao.PaymentDao;
import com.app.dto.PaymentDTO;
import com.app.entity.account.Account;
import com.app.entity.bank.Bank;
import com.app.entity.enums.AccountStatus;
import com.app.entity.enums.TransType;
import com.app.entity.payment.Payment;
import com.app.exceptions.ResourceNotFoundException;
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao payDao;
	@Autowired
	private BankDao bankDao;
	@Autowired
	private AccountDao accDao;
	
	
	@Autowired
	private ModelMapper mapper;
	
	
	
	
	@Override
	public boolean paymentWithinBank(PaymentDTO payments) throws BadRequestException {
	    // Retrieve bank details
	    Bank bank = bankDao.getBankDetails().orElseThrow(() -> new ResourceNotFoundException("Invalid bank details"));
	    payments.setIFSCCode(bank.getBankBranchId());

	    // Map PaymentDTO to Payment entity
	    Payment payment = mapper.map(payments, Payment.class);

	    // Retrieve sender and receiver accounts
	    Account senderAccount = accDao.findById(payments.getSenderAccountNo())
	        .orElseThrow(() -> new ResourceNotFoundException("Invalid sender account"));
	    Account receiverAccount = accDao.findById(payments.getReceiverAccountNo())
	        .orElseThrow(() -> new ResourceNotFoundException("Invalid receiver account"));

	    // Validate account statuses
	    if (senderAccount.getStatus() == AccountStatus.SUSPENDED || senderAccount.getStatus() == AccountStatus.DEACTIVATED 
	        || receiverAccount.getStatus() == AccountStatus.DEACTIVATED) {
	        throw new BadRequestException("Invalid Request: One or both accounts are deactivated or suspended");
	    }

	    double amount = payments.getAmount();

	    // Check sender's balance
	    if (senderAccount.getBalance() < amount) {
	        throw new BadRequestException("Insufficient Funds");
	    }

	    // Apply fee if the payment amount exceeds the limit
	    if (amount > 200000) {
	        double fee = amount * 0.0005;
	        senderAccount.withdraw(fee);
	        bank.addFundAvailable(fee);
	        bankDao.save(bank);
	    }

	    // Perform the transfer
	    senderAccount.withdraw(amount);
	    receiverAccount.deposit(amount);

	    // Save payment details
	    senderAccount.addPayment(payment, receiverAccount);
	    payDao.save(payment);

	    // Save updated accounts
	    accDao.save(senderAccount);
	    accDao.save(receiverAccount);

	    return true;
	}




	@Override
	public String paymentOutsideBank(PaymentDTO payments) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String paymentEMI(PaymentDTO payments) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String paymentLoan(PaymentDTO payments) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
