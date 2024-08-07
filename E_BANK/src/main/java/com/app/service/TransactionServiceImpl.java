package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TransactionHistoryDao;
import com.app.dto.TransactionHistoryDTO;
import com.app.entity.payment.TransactionHistory;

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
    
    @Override
    public List<TransactionHistoryDTO> getAllTransactionHistories() {
    	 List<TransactionHistory> histories = transactionHistoryDao.findAll();
         return histories.stream()
                         .map(history -> mapper.map(history, TransactionHistoryDTO.class))
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
