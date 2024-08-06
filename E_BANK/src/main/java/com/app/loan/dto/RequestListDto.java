package com.app.loan.dto;

import com.app.loan.entities.Status;
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
public class RequestListDto {
	private String requestId;
	private int loanAmount;
	private int loanDuration;
	private String loanType;
	private Status status ;

}
