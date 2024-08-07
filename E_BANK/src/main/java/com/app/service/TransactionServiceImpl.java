package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TransactionHistoryDao;

import com.app.entity.payment.TransactionHistory;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionHistoryDao transactionHistoryDao;

    @Autowired
    private ModelMapper mapper;

    // Method to get all transactions as DTOs
    @Override
    public List<TransactionHistory> getAllTransactions() {
         List<TransactionHistory> transactionHistory = transactionHistoryDao.findAll();
         return transactionHistory;
    }

    // Method to get transactions by account type as DTOs
    @Override
    public List<TransactionHistory> getTransactionsByStatus(String status) {
    	List<TransactionHistory> transactionHistory = transactionHistoryDao.findByStatus(status);
        return transactionHistory;
    }

   

    
}
