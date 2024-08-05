package com.app.service;

import java.util.List;

import com.app.dto.AccountTypeDTO;
import com.app.dto.ApiResponse;
import com.app.entity.account.AccountType;

public interface AccountTypeService {
    
    ApiResponse addAccountType(AccountTypeDTO accTypeDto);
    
    AccountTypeDTO getAccountType(String accType);
    
    String updateAccountType(String accType, float interestRate);
    
    List<AccountType> getAllAccountType();
}
