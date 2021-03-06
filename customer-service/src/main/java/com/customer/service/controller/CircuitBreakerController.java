package com.customer.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.service.dto.CardInfo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CircuitBreakerController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/card-info/circuit-breaker")
	@CircuitBreaker(name = "cardServiceCircuitBreaker", fallbackMethod = "cardFallback")
	public ResponseEntity<?> getCustomerCardInfo() throws InterruptedException {

		ResponseEntity<CardInfo> cInfo = restTemplate
				.getForEntity("http://localhost:7000/api/v1/card/circuit-breaker/1905", CardInfo.class);

		return new ResponseEntity<CardInfo>(cInfo.getBody(), HttpStatus.OK);

	}

	public ResponseEntity<?> cardFallback(Exception e) {

		return new ResponseEntity<CardInfo>(
				CardInfo.builder().cardId(1l).customerId(1l).cardName("CircuitBreaker Default Card!").build(),
				HttpStatus.OK);

	}
}
