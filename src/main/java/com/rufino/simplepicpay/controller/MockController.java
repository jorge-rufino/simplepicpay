package com.rufino.simplepicpay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rufino.simplepicpay.dto.NotificationDto;

@RestController
@RequestMapping("/mocks")
public class MockController {
	
	@PostMapping
	public void notification(@RequestBody NotificationDto notification) {
		System.out.println(notification.message() + " - (" + notification.email()+")");
	}
	
	@GetMapping
	public Map<String, String> authorizationTransaction(){		
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("message", "Autorizado");

		return responseMap;
	}

}
