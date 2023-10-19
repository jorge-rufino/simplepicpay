package com.rufino.simplepicpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rufino.simplepicpay.domain.Transaction;
import com.rufino.simplepicpay.domain.User;
import com.rufino.simplepicpay.dto.TransactionDto;
import com.rufino.simplepicpay.exception.TransactionException;
import com.rufino.simplepicpay.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NotificationService notificationService;
	
	public Transaction createTransaction(TransactionDto transaction) throws Exception {
		User sender = userService.findUserById(transaction.senderId());
		User receiver = userService.findUserById(transaction.receiverId());
		
		userService.validateTransaction(sender, transaction.value());
		
		boolean isAuthorized = authorizeTransaction(sender, transaction.value());
		if(!isAuthorized) {
			throw new TransactionException("Transação não está autorizada!");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmounth(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		repository.save(newTransaction);
		userService.save(sender);
		userService.save(receiver);
		
		notificationService.sendNotification(sender, "Transação efetuada com sucesso! Valor enviado: R$" + transaction.value());
		notificationService.sendNotification(receiver, "Transação recebida com sucesso! Valor recebido: R$" + transaction.value());
		
		return newTransaction;
	}
	
//	Simula uma requisição em um endpoint de terceiro que vai retornar um "message" com valor de "Autorizado" ou "Negado"
	public boolean authorizeTransaction(User sender, BigDecimal value) {
		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("http://localhost:8080/mocks", Map.class);
		
		if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
			String message = (String) authorizationResponse.getBody().get("message");
			
			return "Autorizado".equalsIgnoreCase(message);
		} else {
			return false;
		}
	}

}
