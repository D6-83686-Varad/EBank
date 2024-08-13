package com.app.loan.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Data Transfer Object (DTO) for representing collateral details.
 * This class is used to transfer collateral information between different layers of the application.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CollateralResponseDto {
	
    @NotBlank(message = "Asset cannot be blank")
	private String asset;
	
    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 0")
	private float value;
	
	
	
	private String description;
	

	private String requestId;
}
