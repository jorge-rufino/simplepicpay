package com.rufino.simplepicpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rufino.simplepicpay.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
