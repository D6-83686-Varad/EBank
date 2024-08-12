package com.app.loan.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoanResponseDto {
	
	private String loanNo;
	
	private float loanAmount;
	
	private float emi;

}
