package com.app.loan.dto;

import com.app.loan.entities.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Data Transfer Object (DTO) for representing a list of loan requests.
 * This class is used to transfer summary information about loan requests, typically used in responses that include
 * multiple requests or for displaying request details in a list format.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestListDto {
	
	
	/**
     * The unique identifier for the loan request.
     * This field represents the ID assigned to each loan request, which is used for identification and retrieval.
     */
	private String requestId;
	
	
	/**
     * The amount of money requested for the loan.
     * This field indicates the total amount that the applicant is seeking to borrow.
     */
	private int loanAmount;
	
	
	/**
     * The duration of the loan in months.
     * This field specifies how long the applicant intends to take to repay the loan.
     */
	private int loanDuration;
	
	
	/**
     * The type of loan requested.
     * This field represents the category or type of the loan that the applicant has applied for.
     */
	private String loanType;
	
	
	 /**
     * The current status of the loan request.
     * This field represents the current state or progress of the loan request, such as pending, approved, or rejected.
     */
	private Status status ;

}
