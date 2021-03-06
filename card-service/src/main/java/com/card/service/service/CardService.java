package com.card.service.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.card.service.dto.CardInfo;

@Service
public class CardService {

	private Map<Integer, CardInfo> map;

	@PostConstruct
	private void init() {

		// card 1
		CardInfo card1 = CardInfo.builder().cardId(30l).customerId(20l).cardName("Another card!").build();

		// card 2
		CardInfo card2 = CardInfo.builder().cardId(10l).customerId(1905l).cardName("Tugay's card!").build();

		// map as db
		this.map = Map.of(1, card1, 2, card2);

	}
	
	public CardInfo getCardInfo(long customerId) {
		return map.getOrDefault(customerId, new CardInfo(1l,2l,"default card"));
	}

}
