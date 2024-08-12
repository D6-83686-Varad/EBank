package com.app.loan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoanDetailResponse {
	private String loanNo;
    private double loanAmount;
    private double remainingAmount;
    private double emi;
    private String loanName;
    private double interestRate;
    private int tenure;
	private String asset;
	private String value;
	
}
