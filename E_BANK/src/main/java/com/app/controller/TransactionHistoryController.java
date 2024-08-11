package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.TransactionHistoryDTO;
import com.app.service.TransactionService;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/transactions")
public class TransactionHistoryController {

    @Autowired
    private TransactionService transactionHistoryService;

    /**
     * Get a list of transaction histories based on status.
     * 
     * @param status the status of the transactions to retrieve
     * @return a ResponseEntity containing the list of TransactionHistoryDTOs and HTTP status
     */
    @GetMapping("/transactions/bystatus")
    public ResponseEntity<List<TransactionHistoryDTO>> getTransactionsByStatus(
            @RequestParam("status") String status) {
        try {
            List<TransactionHistoryDTO> transactions = transactionHistoryService.getTransactionsByStatus(status);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions (e.g., log the error, return an error response)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get all transaction history details.
     * 
     * @return a ResponseEntity containing a list of TransactionHistoryDTOs and HTTP status
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionHistoryDTO>> getAllTransactionHistories() {
        try {
            List<TransactionHistoryDTO> transactionHistories = transactionHistoryService.getAllTransactionHistories();
            if (transactionHistories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transactionHistories, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception (e.g., logger.error("Failed to get all transaction histories", e))
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/account/{accountNo}/month/{month}")
    public List<TransactionHistoryDTO> getTransactionsByAccountNoAndMonth(
            @PathVariable String accountNo,
            @PathVariable int month) {
        return transactionHistoryService.getTransactionsByAccountNoAndMonth(accountNo, month);
    }
    
    @GetMapping("/account/{accountNo}")
    public List<TransactionHistoryDTO> getTransactionsByAccountNo(@PathVariable String accountNo) {
        return transactionHistoryService.getTransactionsByAccountNo(accountNo);
    }
}
