package com.customer.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.service.dto.CardInfo;
import com.customer.service.service.BulkheadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BulkheadController {

	private final Long customerId = 1905l;
	private final BulkheadService bulkheadService;

	@GetMapping(value = "/card-info/bulkhead")
	public ResponseEntity<?> getCustomerCardInfo() throws InterruptedException {
		
		 for (int i = 0; i < 9; i++) {
	            new Thread(() -> {
	                try {
	                	bulkheadService.getCardInfo(customerId,Thread.currentThread().getName());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }, "service-call-"+i).start();
	            Thread.sleep(50);
	        }

		return new ResponseEntity<CardInfo>(bulkheadService.getCardInfo(customerId,Thread.currentThread().getName()),
				org.springframework.http.HttpStatus.OK);
	}

}
