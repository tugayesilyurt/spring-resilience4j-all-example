package com.customer.service.service;

import org.springframework.stereotype.Service;

import com.customer.service.dto.CardInfo;
import com.customer.service.feign.client.CardServiceClient;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BulkheadService {

	private final CardServiceClient cardServiceClient;
	
	@Bulkhead(name = "cardServiceBulkhead", fallbackMethod = "bulkHeadFallback")
	public CardInfo getCardInfo(long customerId,String threadName) {
		
		CardInfo responseCardInfo = cardServiceClient.getCardInfoByCustomerId(customerId);

		return responseCardInfo;
		
	}
	
    public CardInfo bulkHeadFallback(long customerId,String threadName, Throwable t) {
        log.error("Inside bulkHeadFallback, cause - {}", t.toString());
        log.error("Inside bulkHeadFallback, Thread Name - {}", threadName);
        return CardInfo.builder().cardId(1l).customerId(1l).cardName("Bulkhead Default Card!").build();
    }

}
