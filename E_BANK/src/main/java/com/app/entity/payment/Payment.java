package com.app.entity.payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.app.entity.account.Account;
import com.app.entity.base.BaseEntity;
import com.app.id.generator.DatePrefixedSequenceIdGenerator;
import com.app.id.generator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Payment extends BaseEntity {
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_seq")
	    @GenericGenerator(
	        name = "pay_seq", 
	        strategy = "org.thoughts.on.java.generators.DatePrefixedSequenceIdGenerator", 
	        parameters = {@Parameter(name = DatePrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50")})
	@Column(name="ref_id")
	private String refId;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_acc_No")
	private Account receicerAccount;
	
	@Column
	private boolean status=false;
	@Column(name="amount")
	private double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_acc_No")
	private Account senderAccount;
	
	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionHistory> transactionHistories = new ArrayList<>();
	
	

	
}
