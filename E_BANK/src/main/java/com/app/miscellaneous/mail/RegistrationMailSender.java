package com.app.miscellaneous.mail;

import com.app.entity.account.AccountType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@NoArgsConstructor
public class RegistrationMailSender {
	
	String message="Dear Customer Your account is succefully created.";
	String subject ="Bank: Verification";
	String to="vighnesh.work@gmail.com";
	String from="verifyuserdetails.001@gmail.com";
	

public RegistrationMailSender(String mail,String accountNo,double balance,long customerId)
	{
		String message="Dear Customer Your account is succefully created"+"your account no"+accountNo+"your current balance"+balance+"customerId"+customerId;
		String subject ="Bank: Verification";
		String to=mail;
		String from="verifyuserdetails.001@gmail.com";
	}
//accno,balance,acctype,customer_id
}
