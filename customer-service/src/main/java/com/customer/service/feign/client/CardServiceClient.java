package com.customer.service.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.service.dto.CardInfo;

@Component
@FeignClient("CARD-SERVICE")
public interface CardServiceClient {
	
	@GetMapping(value = "/api/v1/card/bulkhead/{customerId}")
	CardInfo getCardInfoByCustomerId(@PathVariable("customerId") Long customerId);

	@GetMapping(value = "/api/v1/card/circuit-breaker/{customerId}")
	CardInfo getCardInfoByCustomerIdCircuit(@PathVariable("customerId") Long customerId);

	@GetMapping(value = "/api/v1/card/retry/{customerId}")
	CardInfo getCardInfoByCustomerIdRetry(@PathVariable("customerId") Long customerId);

	@GetMapping(value = "/api/v1/card/timeout/{customerId}")
	CardInfo getCardInfoByCustomerIdTimeout(@PathVariable("customerId") Long customerId);

}
