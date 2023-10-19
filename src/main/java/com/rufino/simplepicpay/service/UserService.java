package com.rufino.simplepicpay.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rufino.simplepicpay.domain.User;
import com.rufino.simplepicpay.domain.UserType;
import com.rufino.simplepicpay.exception.EntidadeNaoEncontradaException;
import com.rufino.simplepicpay.exception.MerchantException;
import com.rufino.simplepicpay.exception.SaldoInsuficienteException;
import com.rufino.simplepicpay.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amounth) throws Exception{
		
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new MerchantException("Um usuário do tipo lojista não pode fazer transações.");
		}
		
		if(sender.getBalance().compareTo(amounth) < 0) {
			throw new SaldoInsuficienteException("Saldo insuficiente!");
		}
	}
	
	public User findUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}
}
