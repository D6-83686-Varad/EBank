package com.app.loan.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoanDetailsDto {
	private String loanName;
	private float interestRate;
	private int tenure;
	private int maxAmount;
	private int minAmount;
}
