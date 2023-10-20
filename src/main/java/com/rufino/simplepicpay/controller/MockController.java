package com.rufino.simplepicpay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> notification(@RequestBody NotificationDto notification) {
		boolean aux = true;
		
		if(aux) {
			System.out.println(notification.message() + " - (" + notification.email()+")");
			
			return ResponseEntity.ok().build();
		}
		else {			
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping
	public Map<String, String> authorizationTransaction(){		
		Map<String, String> responseMap = new HashMap<>();
		
		List<String> options = new ArrayList<>();
		options.add("Autorizado");
		options.add("Não autorizado");
		
		Random random = new Random();
		
		responseMap.put("message", options.get(random.nextInt(options.size())));

		return responseMap;
	}

}
