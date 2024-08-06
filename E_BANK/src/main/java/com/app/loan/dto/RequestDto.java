package com.app.loan.dto;

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
public class RequestDto {
	
	private int loanAmount;
	private int loanDuration;
	private String loanType;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String accountId;
}
