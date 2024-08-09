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
	
//	String message="Dear Customer Your account is succefully created.";
//	String subject ="Bank: Verification";
//	String to="agsahu222@gmail.com";
//	String from="verifyuserdetails.001@gmail.com";
//	String accountName;
	
	
	private String message;
	private String subject;
	private String to;
	
	public RegistrationMailSender(String to, String accountNo,double balance,long customerId, String accountName,String bankName ) {
		this.message = "Dear Customer Your account is succefully created \n"+"your account no is:"+accountNo+"\nyour current balance is:"+balance+"\nyour ID is:"+customerId+"\nyour account type is:"+accountName+"\nBest Regards\n"+bankName;
		this.subject = "Bank: Verification";
		this.to = to;
	}
	
	public RegistrationMailSender(String to, String accountNo, String requestId, String bankName) {
		this.message = "Dear Customer your Loan request for Request Id: "+ requestId +"is accepted \n" + "Please provide Collateral information.\n"+ "Your Account Number: "+ accountNo + "\n Best Regards \n"+ bankName;
		this.subject = "Bank: Verification";
		this.to = to;
	}
	
	
	

//public RegistrationMailSender(String mail,String accountNo,double balance,long customerId, String accountName)
//	{
//		 message="Dear Customer Your account is succefully created"+"your account no"+accountNo+"your current balance"+balance+"customerId"+customerId+"name"+accTypeName;
//		subject ="Bank: Verification";
//		to=mail;
//		 from="verifyuserdetails.001@gmail.com";
//		accountName = accountName;
//	}
//accno,balance,acctype,customer_id
	
	
	
}
