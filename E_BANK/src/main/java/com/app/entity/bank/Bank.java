package com.app.entity.bank;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.app.id.generator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_seq")
    @GenericGenerator(
        name = "bank_seq", 
        strategy = "com.app.id.generator.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "2"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "JWT_"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String bankBranchId;
	@Column(name="bank_name", length=20,nullable=false,unique = true)
	private String bankName;
	@Column(name="contact_email",length=20,nullable=false,unique=true)
	private String contactEmail;

	//FUNDS
	@Column
	private double fundAvailable=0;
	@Column
	private double fundReceived=0;
	@Column
	private double fundToPay=0;
	
	//Loan
	@Column
	private double loanDisbursed=0;
	@Column
	private double loanRecovered=0;
	@Column
	private double loanExpected=0;
}
