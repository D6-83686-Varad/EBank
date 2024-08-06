package com.app.loan.service;

import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.CollateralDto;
import com.app.loan.entities.Collateral;

public interface CollateralService {
	ApiResponse addCollateral(CollateralDto collateralDto);
}
