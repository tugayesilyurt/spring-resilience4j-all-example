package com.customer.service.controller;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.service.dto.CardInfo;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RetryController {

	@Autowired
	private RestTemplate restTemplate;

	AtomicInteger countCall = new AtomicInteger();

	@GetMapping(value = "/card-info/retry")
	@Retry(name = "cardServiceRetry", fallbackMethod = "getRetryFallback")
	public ResponseEntity<?> getCustomerCardInfo() throws InterruptedException {

		System.out
				.println("/card-info/retry count : " + countCall.incrementAndGet() + " Time : " + LocalDateTime.now());

		ResponseEntity<CardInfo> cInfo = restTemplate
				.getForEntity("http://localhost:7000/api/v1/card/retry/1905", CardInfo.class);

		return new ResponseEntity<CardInfo>(cInfo.getBody(), HttpStatus.OK);
	}

	public ResponseEntity<?> getRetryFallback(Exception e) {

		return new ResponseEntity<CardInfo>(
				CardInfo.builder().cardId(1l).customerId(1l).cardName("Retry pattern fallback Card!").build(),
				HttpStatus.OK);

	}

}
