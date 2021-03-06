package com.card.service.controller;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.service.dto.CardInfo;
import com.card.service.service.CardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@Slf4j
public class CardController {

	private final CardService cardService;
	AtomicInteger data = new AtomicInteger();

	@GetMapping(value = "/bulkhead/{customerId}")
	public ResponseEntity<?> getCardInfoByCustomerId(@PathVariable("customerId") long customerId) throws InterruptedException {
		
		log.info("Bulkhead Card Info Call "+ Thread.currentThread().getName());
		log.info("Bulkhead Card Info Time "+ LocalDateTime.now());
		log.info("Atomic " + data.getAndIncrement());
		Thread.sleep(2000);
		
		return new ResponseEntity<CardInfo>(cardService.getCardInfo(customerId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/circuit-breaker/{customerId}")
	public ResponseEntity<?> getCardInfoByCustomerIdCircuit(@PathVariable("customerId") long customerId){
		
		log.info("circuit-breaker Card Info Call "+ Thread.currentThread().getName());
		log.info("circuit-breaker Card Info Time "+ LocalDateTime.now());
		log.info("Atomic " + data.getAndIncrement());
		throw new RuntimeException("circuit breaker pattern runtime exception");
		
	}
	
	@GetMapping(value = "/retry/{customerId}")
	public ResponseEntity<?> getCardInfoByCustomerIdRetry(@PathVariable("customerId") long customerId){
		
		log.info("retry Card Info Call "+ Thread.currentThread().getName());
		log.info("retry Card Info Time "+ LocalDateTime.now());
		log.info("Atomic " + data.getAndIncrement());
		throw new RuntimeException("retry pattern runtime exception");
		
	}
	
	@GetMapping(value = "/timeout/{customerId}")
	public ResponseEntity<?> getCardInfoByCustomerIdTimeout(@PathVariable("customerId") long customerId) throws InterruptedException{
		
		log.info("Timeout Card Info Call "+ Thread.currentThread().getName());
		log.info("Timeout Card Info Time "+ LocalDateTime.now());
		log.info("Atomic " + data.getAndIncrement());
		Thread.sleep(4000);
		
		return new ResponseEntity<CardInfo>(cardService.getCardInfo(customerId), HttpStatus.OK);
	}
}
