package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TransactionHistoryDao;
import com.app.dto.TransactionHistoryDTO;
import com.app.entity.account.Account;
import com.app.entity.payment.Payment;
import com.app.entity.payment.TransactionHistory;
import com.app.loan.entities.LoanPayment;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionHistoryDao transactionHistoryDao;

    @Autowired
    private ModelMapper mapper;

    // Method to get all transactions as DTOs
    public List<TransactionHistoryDTO> getTransactionsByStatus(String status) {
        List<TransactionHistory> histories = transactionHistoryDao.findByStatusWithAccountsAndPayments(status);
        return histories.stream()
                        .map(history -> mapper.map(history, TransactionHistoryDTO.class))
                        .collect(Collectors.toList());
    }
    
//    @Override
//    public List<TransactionHistoryDTO> getAllTransactionHistories() {
//    	 List<TransactionHistory> histories = transactionHistoryDao.findAll();
//         return histories.stream()
//                         .map(history -> mapper.map(history, TransactionHistoryDTO.class))
//                         .collect(Collectors.toList());
    @Override
    public List<TransactionHistoryDTO> getAllTransactionHistories() {
        List<TransactionHistory> histories = transactionHistoryDao.findAll();
        return histories.stream()
                        .map(history -> {
                            // Map TransactionHistory to TransactionHistoryDTO
                            TransactionHistoryDTO dto = mapper.map(history, TransactionHistoryDTO.class);

                            // Fetch and set additional details
                            Account account = history.getAccount(); // Replace with actual method to get Account
                            if (account != null) {
                                dto.setAccountId(account.getAccountNo()); // Replace with actual getter for account ID
                            }

                            Payment payment = history.getPayment(); // Replace with actual method to get Payment
                            if (payment != null) {
                                dto.setPaymentId(payment.getRefId()); // Replace with actual getter for payment ID
                            }

                            LoanPayment loanPayment = history.getLoanPayment(); // Replace with actual method to get LoanPayment
                            if (loanPayment != null) {
                               dto.setPayment_id(loanPayment.getPayment_id()); // Replace with actual getter for loan payment ID
                            }

                            return dto;
                        })
                        .collect(Collectors.toList());
    }
    
    @Override
    public List<TransactionHistoryDTO> getTransactionsByAccountNoAndMonth(String accountNo, int month) {
    	 List<TransactionHistory> histories = transactionHistoryDao.findByAccountNoAndMonth(accountNo, month);
         return histories.stream()
                         .map(history -> mapper.map(history, TransactionHistoryDTO.class))
                         .collect(Collectors.toList());
    }
    @Override
    public List<TransactionHistoryDTO> getTransactionsByAccountNo(String accountNo) {
    	 List<TransactionHistory> histories = transactionHistoryDao.findByAccountNo(accountNo);
         return histories.stream()
                         .map(history -> mapper.map(history, TransactionHistoryDTO.class))
                         .collect(Collectors.toList());
    }

   

    
}
