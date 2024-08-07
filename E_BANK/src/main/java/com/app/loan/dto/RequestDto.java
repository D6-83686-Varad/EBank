package com.app.loan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Data Transfer Object (DTO) for representing loan requests.
 * This class is used to transfer information related to loan requests between different layers of the application.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestDto {
	
	 /**
     * The amount of money requested for the loan.
     * This field represents the total amount that the applicant is requesting to borrow.
     */
	private int loanAmount;
	
	
	 /**
     * The duration of the loan in months.
     * This field specifies the period over which the loan is to be repaid.
     */
	private int loanDuration;
	
	
	/**
     * The type of loan requested.
     * This field indicates the specific category or type of loan that the applicant is applying for.
     */
	private String loanType;
	
	
	/**
     * The ID of the account associated with the loan request.
     * This field is used internally to link the request to the specific account. It is excluded from JSON serialization
     * when reading (i.e., not exposed to clients) but can be included in requests to the server for creating or updating loan requests.
     */
	@JsonProperty(access = Access.WRITE_ONLY)
	private String accountId;
}
