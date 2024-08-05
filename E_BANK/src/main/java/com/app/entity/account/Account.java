package com.app.entity.account;

import java.math.BigDecimal;
import java.util.ArrayList;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.sound.midi.Receiver;
import javax.transaction.Transaction;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;


import com.app.entity.bank.Bank;
import com.app.entity.base.BaseEntity;
import com.app.entity.customer.Customer;
import com.app.entity.customer.LoginUser;
import com.app.entity.enums.AccountStatus;
import com.app.entity.payment.Payment;
import com.app.entity.payment.TransactionHistory;
import com.app.id.generator.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Account extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
    @GenericGenerator(
        name = "acc_seq", 
        strategy = "com.app.id.generator.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SPB"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
	@Column(name="account_no")
	private String accountNo;
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	@Column(name="balance")
	private BigDecimal balance;

	@Column
	private boolean isDeleted=false;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Type_Fk_Id",nullable= false)
	private AccountType accType;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bankId",nullable=false)
	private Bank bank;
	//nkoy
//	@ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "bankCashflowId",nullable=false)
//	private BankCashFlow bankcash;
	
	@OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Payment> paymentsMade = new ArrayList<>();
	
	@OneToMany(mappedBy = "receicerAccount", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<Payment> paymentsReceive = new ArrayList<>();
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<TransactionHistory> transactionHistories = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id",nullable= false)
	private Customer customer;
	
	@OneToOne(mappedBy = "account")
	private LoginUser user;
	
	
//	public void deposit(BigDecimal amount,Account account)
//	{
//		this.balance = this.getBalance()- amount;
//		account.balance =account.getBalance()+ amount;
//	}
//
//
//
//	public void addPayment(Payment payment, Account receiverAccount) {
//		this.getPaymentsMade().add(payment);
//		payment.setSenderAccount(this);
//		receiverAccount.getPaymentsReceive().add(payment);
//		payment.setReceicerAccount(receiverAccount);
//		
//	}
//
//
//
//	public void addTransactionHistory(PaymentDTO pay, TransactionHistory senderTransHistory, Payment payment) {
//		// TODO Auto-generated method stub
//		senderTransHistory.setDescription(pay.getDescription());
//		senderTransHistory.setTransactionType("Debit");
//		senderTransHistory.setAmount(pay.getAmount());
//		//System.out.println(senderAccount.getBalance());
//		senderTransHistory.setBalance(this.getBalance()-senderTransHistory.getAmount());
//		//System.out.println(senderAccount.getBalance()+"--------");
//		senderTransHistory.setStatus("Success");
//		senderTransHistory.setTransactionMode(pay.getTransactionMode());
//		this.getTransactionHistories().add(senderTransHistory);
//		senderTransHistory.setAccount(this);
//		payment.getTransactionHistories().add(senderTransHistory);
//		senderTransHistory.setPayment(payment);
//	}
//	
//	public void addPayment(Payment payment) {
//		this.getPaymentsMade().add(payment);
//		payment.setSenderAccount(this);
//		
//		
//	}



	
	

	
	
	
	
	

}
