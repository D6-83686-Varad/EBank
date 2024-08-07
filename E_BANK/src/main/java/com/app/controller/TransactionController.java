package com.app.controller;

import com.app.entity.payment.TransactionHistory;
import com.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to get all transactions
    @GetMapping
    public List<TransactionHistory> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Endpoint to get transactions by status
    @GetMapping("/status/{status}")
    public List<TransactionHistory> getTransactionsByStatus(@PathVariable String status) {
        return transactionService.getTransactionsByStatus(status);
    }
}
