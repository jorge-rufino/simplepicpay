package com.rufino.simplepicpay.domain;

import java.math.BigDecimal;

import com.rufino.simplepicpay.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	
	@Column(unique = true, length = 18)	
	private String document;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;

	public User(UserDto userDto) {
		this.firstName = userDto.firstName();
		this.lastName = userDto.lastName();
		this.document = userDto.document();
		this.email = userDto.email();
		this.balance = userDto.balance();
		this.password = userDto.password();
		this.userType = userDto.userType();
	}
}
