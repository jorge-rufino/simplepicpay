package com.rufino.simplepicpay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rufino.simplepicpay.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findUserByDocument(String document);
}
