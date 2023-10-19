package com.rufino.simplepicpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rufino.simplepicpay.domain.Transaction;
import com.rufino.simplepicpay.dto.TransactionDto;
import com.rufino.simplepicpay.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) throws Exception{
		Transaction newTransaction = transactionService.createTransaction(transactionDto);
		
		return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
	}
}
