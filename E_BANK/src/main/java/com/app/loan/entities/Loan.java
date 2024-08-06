package com.app.loan.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.app.loan.idGenerator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Loan extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_seq")
    @GenericGenerator(
        name = "loan_seq", 
        strategy = "com.app.loan.idGenerator.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "L_"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") })
	@Column(name="loan_no")
	private String loanNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_id", nullable = false)
	private Account account_id;
	
	@Column(name="loan_amount")
	private int loanAmount;
	
	@Column(name="emi")
	private int emi;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="loan_status")
	private char loanStatus= 'O';
	
	@ManyToOne
	@JoinColumn(name="loan_type", nullable = false)
	private LoanDetails loanDetails;
	
	public Loan(Account account_id, int loanAmount, int emi, LocalDate startDate, LocalDate endDate,
			LoanDetails loanDetails, Collateral collateralId) {
		super();
		this.account_id = account_id;
		this.loanAmount = loanAmount;
		this.emi = emi;
		this.startDate = startDate;
		this.endDate = endDate;
		this.loanDetails = loanDetails;
		this.collateralId = collateralId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="collateral_id", nullable = false)
	private Collateral collateralId;
}
