package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.account.Account;
import com.app.service.AccountSevice;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountSevice accService;
	

	@GetMapping("/actiavted")
	public ResponseEntity<?> getAllActivatedAccounts()
	{
		List<Account> activatedAccounts =accService.getAllActivatedAccount();
		if(activatedAccounts.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(activatedAccounts);
	}
	
	@GetMapping("/deactivated")
	public ResponseEntity<?> getAllDeactivatedAccounts()
	{
		List<Account> deactivatedAccounts =accService.getAllDeactivatedAccount();
		if(deactivatedAccounts.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(deactivatedAccounts);
	}
	
	@GetMapping("/suspended")
	public ResponseEntity<?> getAllSuspendedAccounts()
	{
		List<Account> suspendedAccounts =accService.getAllSuspendedAccount();
		if(suspendedAccounts.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(suspendedAccounts);
	}
	@PatchMapping("/{accId}/updateStatusA")
	public ResponseEntity<?> changeToActivate(@PathVariable("accId")String accId)
	{
		return ResponseEntity.ok(accService.changeStatusOfActivatedAccount(accId));
	}
	@PatchMapping("/{accId}/updateStatusD")
	public ResponseEntity<?> changeToDeactivate(@PathVariable("accId")String accId)
	{
		return ResponseEntity.ok(accService.changeStatusOfDeactivatedAccount(accId));
	}
	

}
