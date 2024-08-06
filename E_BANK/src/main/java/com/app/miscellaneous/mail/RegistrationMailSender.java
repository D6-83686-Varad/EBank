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
	
	public RegistrationMailSender(String mail)
	{
		String message="Dear Customer Your account is succefully created.";
		String subject ="Bank: Verification";
		String to=mail;
		String from="verifyuserdetails.001@gmail.com";
	}

}
