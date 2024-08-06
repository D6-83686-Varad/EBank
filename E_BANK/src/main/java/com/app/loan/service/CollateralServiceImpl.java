package com.app.loan.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.loan.dao.CollateralDao;
import com.app.loan.dao.RequestDao;
import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.CollateralDto;
import com.app.loan.entities.Collateral;
import com.app.loan.entities.Request;
import com.app.loan.exceptions.ApiException;

@Service
@Transactional
public class CollateralServiceImpl implements CollateralService{
	@Autowired
	private CollateralDao collDao;
	
	@Autowired
	private RequestDao reqDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addCollateral(CollateralDto collateralDto) {
		// TODO Auto-generated method stub
		System.out.println(collateralDto.toString());
		System.out.println(collateralDto.getRequestId());
		Request req = reqDao.findById(collateralDto.getRequestId()).orElseThrow();
		Collateral collateral = mapper.map(collateralDto, Collateral.class);
		req.addCollateral(collateral);
		return new ApiResponse("Added a new Collateral");
	}
	
	
	
}
