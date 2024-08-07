package com.app.loan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Data Transfer Object (DTO) for representing loan details.
 * This class is used to transfer loan information between different layers of the application.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoanDetailsDto {
	
	/**
     * The name or type of the loan.
     * This field describes the specific category or name given to the loan product.
     */
	private String loanName;
	
	
	/**
     * The interest rate applicable to the loan.
     * This field represents the percentage rate at which interest is charged on the loan.
     */
	private float interestRate;
	
	
	/**
     * The tenure of the loan in months.
     * This field indicates the total duration over which the loan is to be repaid.
     */
	private int tenure;
	
	
	/**
     * The maximum amount that can be borrowed under this loan type.
     * This field specifies the upper limit of the loan amount that can be approved.
     */
	private int maxAmount;
	
	
	 /**
     * The minimum amount that can be borrowed under this loan type.
     * This field specifies the lower limit of the loan amount that can be approved.
     */
	private int minAmount;
}
