package com.app.service;

import org.apache.coyote.BadRequestException;

import com.app.dto.PaymentDTO;

public interface PaymentService {
	

	boolean paymentWithinBank(PaymentDTO payments) throws BadRequestException;
	String paymentOutsideBank(PaymentDTO payments);
	String paymentEMI(PaymentDTO payments);
	String paymentLoan(PaymentDTO payments);
}
