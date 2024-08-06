package com.app.service;

import java.util.List;

import com.app.dto.AccountTypeDTO;
import com.app.dto.ApiResponse;
import com.app.entity.account.AccountType;

public interface AccountTypeService {

    AccountTypeDTO getAccountType(String acc);

    List<AccountType> getAllAccountType();
	String updateAccountType(String acc, float interestRate);
	ApiResponse addAccType(AccountTypeDTO acc);
}
