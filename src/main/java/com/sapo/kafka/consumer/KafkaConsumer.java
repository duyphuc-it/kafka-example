package com.sapo.kafka.consumer;

import com.sapo.dto.Account;
import com.sapo.dto.Order;
import com.sapo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapo.constant.ApplicationConstant;

import java.util.Optional;

@Component
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	AccountRepository accountRepository;

	@KafkaListener(groupId = ApplicationConstant.GROUP_ID_JSON, topics = ApplicationConstant.TOPIC_NAME, containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void receivedMessage(Order message) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(message);
		logger.info("Json message received using Kafka listener " + jsonString);
		if(message.getAccountId() != null) {
			Optional<Account> accountOptional = accountRepository.findById(message.accountId);
			if(accountOptional.isPresent()) {
				Account account = accountOptional.get();
				account.setSpentMoney(account.getSpentMoney().add(message.getTotalPrice()));
				accountRepository.save(account);
			}
		}
	}
}
