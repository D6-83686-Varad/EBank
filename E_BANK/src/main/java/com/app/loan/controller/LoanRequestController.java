package com.app.loan.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.loan.dto.RequestDto;
import com.app.loan.entities.Request;
import com.app.loan.service.CollateralService;
import com.app.loan.service.LoanService;
import com.app.loan.service.RequestService;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {
	@Autowired 
	private RequestService reqService;
	
	
	//Administrator Side API
	
	// Administrator will have the access to see status of loan request
	
	// By default when customer is going to apply for loan , status of his request is going to be "requested"
	@GetMapping("/requested")
	public ResponseEntity<?> getAllRequestedAccount()
	{
		System.out.println("Hii");
		List<Request> accList =reqService.viewRequested();
		if(accList.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accList);
	}
	
	
	@GetMapping("/pending")
	public ResponseEntity<?> getAllPendingAccount()
	{
		List<Request> accList =reqService.viewPending();
		if(accList.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accList);
	}
	
	@GetMapping("/approved")
	public ResponseEntity<?> getAllApprovedAccount()
	{
		List<Request> accList =reqService.viewApproved();
		if(accList.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accList);
	}
	
	@GetMapping("/declined")
	public ResponseEntity<?> getAllDeclinedAccount()
	{
		List<Request> accList =reqService.viewDeclined();
		if(accList.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accList);
	}
	
	@PatchMapping("/pending/{id}")
	public ResponseEntity<?> updateToPending(@PathVariable("id") String id){
		return ResponseEntity.ok(reqService.updateToPending(id));
	}
	
	@PatchMapping("/approved/{id}")
	public ResponseEntity<?> updateToApproved(@PathVariable("id") String id){
//		reqService.updateToApproved(id);
//		Request reqq= re
		return ResponseEntity.ok(reqService.updateToApproved(id));
	}
	
	@PatchMapping("/declined/{id}")
	public ResponseEntity<?> updateToDeclined(@PathVariable("id") String id){
		return ResponseEntity.ok(reqService.updateToDeclined(id));
	}
	
	// Customer Side API -> Customer will have only Add Request
	// Once he submits the request administrator is going to check for conditions for loan approval
	// If all conditions meets then he will send an email to Customer for their loan eligibility and asks for Collateral Details and Status will be set to pending in Loan Request table  
	@PostMapping("/add")
	public ResponseEntity<?> addLoanRequest(@RequestBody RequestDto requestDto){
		System.out.println("Hii in controller");
		System.out.println(requestDto.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(reqService.addRequest(requestDto));
		
	}
	
	
}
