package com.customer.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.service.dto.CardInfo;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RatelimiterController {

	@GetMapping(value = "/card-info/ratelimiter")
	@RateLimiter(name = "cardServiceRatelimiter", fallbackMethod = "ratelimiterFallback")
	public ResponseEntity<?> getCustomerCardInfo() throws InterruptedException {

		return new ResponseEntity<CardInfo>(
				CardInfo.builder().cardId(1l).customerId(1l).cardName("Ratelimiter Card!").build(),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> ratelimiterFallback(Exception e) {

		return new ResponseEntity<CardInfo>(
				CardInfo.builder().cardId(1l).customerId(1l).cardName("Ratelimiter fallback Card!").build(),
				HttpStatus.OK);

	}

}
