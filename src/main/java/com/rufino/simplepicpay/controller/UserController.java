package com.rufino.simplepicpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rufino.simplepicpay.domain.User;
import com.rufino.simplepicpay.dto.UserDto;
import com.rufino.simplepicpay.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
		User newUser = new User(userDto);
		
		return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		List<User> users = userService.findAll();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
