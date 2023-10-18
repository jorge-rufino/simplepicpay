package com.rufino.simplepicpay.dto;

import java.math.BigDecimal;

import com.rufino.simplepicpay.domain.UserType;

public record UserDto(String firstName, String lastName, String document, String email, BigDecimal balance, String password, UserType userType  ) {

}
