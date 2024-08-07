package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.payment.TransactionHistory;


@Repository
public interface TransactionHistoryDao extends JpaRepository<TransactionHistory, String> {
	List<TransactionHistory> findByStatus(String status);
}
