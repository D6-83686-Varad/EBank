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
public class CollateralDto {
	
	private String asset;
	private float value;
	private String description;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String requestId;
}
