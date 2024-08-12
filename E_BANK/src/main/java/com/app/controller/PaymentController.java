package com.app.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.PaymentDTO;
import com.app.exceptions.ResourceNotFoundException;
import com.app.service.PaymentService;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/within-bank")
    public ResponseEntity<String> paymentWithinBank(@RequestBody PaymentDTO paymentDTO) {
        try {
            boolean result = paymentService.paymentWithinBank(paymentDTO);
            if (result) {
                return new ResponseEntity<>("Payment within bank was successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Payment within bank failed", HttpStatus.BAD_REQUEST);
            }
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/outside-bank")
    public ResponseEntity<String> paymentOutsideBank(@RequestBody PaymentDTO paymentDTO) {
        try {
            boolean result = paymentService.paymentOutsideBank(paymentDTO);
            if (result) {
                return ResponseEntity.ok("Payment processed successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed.");
            }
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the payment.");
        }
    }
}

