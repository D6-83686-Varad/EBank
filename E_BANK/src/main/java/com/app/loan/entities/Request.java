package com.app.loan.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.app.loan.idGenerator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="request")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Request extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_sequence")
	@GenericGenerator(
		        name = "request_sequence", 
		        strategy = "com.app.loan.idGenerator.StringPrefixedSequenceIdGenerator", 
		        parameters = {
		            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
		            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "R_"),
		            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") }
		        )
	@Column(name="request_id")
	private String requestId;
	
	@Column(name = "loan_amount")
	private int loanAmount;
	
	@Column(name="loan_duration")
	private int loanDuration;

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status = Status.R;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name="account_number", nullable = false)
	private Account account;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="loan_type", nullable = false)
	private LoanDetails details;

	@OneToMany(mappedBy = "request", cascade = CascadeType.ALL,  orphanRemoval = true)
	@JsonIgnore
	private List<Collateral> collaterals = new ArrayList<>();
	
	public void addCollateral(Collateral collateral) {
		this.collaterals.add(collateral);
		collateral.setRequest(this);
	}
	
}
