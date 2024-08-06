package com.app.loan.service;

import java.util.List;

import com.app.loan.dto.ApiResponse;
import com.app.loan.dto.RequestDto;
import com.app.loan.entities.Request;

public interface RequestService {
	ApiResponse addRequest(RequestDto reqDto);
	List<Request> viewPending();
	List<Request> viewRequested();
	List<Request> viewApproved();
	List<Request> viewDeclined();
	String updateToPending(String requestId);
	String updateToApproved(String requestId);
	String updateToDeclined(String requestId);
}
