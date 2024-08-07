package com.app.service;

import java.util.List;


import com.app.entity.payment.TransactionHistory;

public interface TransactionService {
    List<TransactionHistory> getAllTransactions();
    List<TransactionHistory> getTransactionsByStatus(String accountType);
}
