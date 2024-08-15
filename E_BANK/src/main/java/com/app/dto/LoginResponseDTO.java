package com.app.dto;

import com.app.entity.account.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

	private long customerId;
	
    private String email;

    private String role;

    private String phoneNumber;
    
    private String accountNo;
     
    private String jwtToken;
}
