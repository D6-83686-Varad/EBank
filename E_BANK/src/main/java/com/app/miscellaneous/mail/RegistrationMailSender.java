package com.app.miscellaneous.mail;

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
	
	public RegistrationMailSender(String mail,String accountNo,double balance,String accTypeId,long customerId)
	{
		String message="Dear Customer Your account is succefully created."+"accountno"+accountNo+"balance"+balance+"account ID"+accTypeId+"customer ID"+customerId;
		String subject ="Bank: Verification";
		String to=mail;
		String from="verifyuserdetails.001@gmail.com";
	}
//accno,balance,acctype,customer_id
}
