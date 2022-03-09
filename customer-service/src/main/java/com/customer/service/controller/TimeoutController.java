package com.customer.service.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.service.dto.CardInfo;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TimeoutController {

	@Autowired
	private RestTemplate restTemplate;

	AtomicInteger countCall = new AtomicInteger();

	@GetMapping(value = "/card-info/timeout")
	@TimeLimiter(name = "cardServiceTime", fallbackMethod = "getTimeoutFallback")
	public CompletionStage<?> getCustomerCardInfo() throws InterruptedException {

		Supplier<CardInfo> supplier = () -> this.restTemplate
				.getForEntity("http://localhost:7000/api/v1/card/timeout/1905", CardInfo.class).getBody();

		return CompletableFuture.supplyAsync(supplier);
	}

	public CompletionStage<?> getTimeoutFallback(Exception e) {
		return CompletableFuture.supplyAsync(
				() -> CardInfo.builder().cardId(1l).customerId(1l).cardName("Timeout pattern fallback Card!").build());
	}

}
