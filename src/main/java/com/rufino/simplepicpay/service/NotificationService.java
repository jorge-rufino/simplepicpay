package com.rufino.simplepicpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rufino.simplepicpay.domain.User;
import com.rufino.simplepicpay.dto.NotificationDto;
import com.rufino.simplepicpay.exception.NotificationException;

@Service
public class NotificationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String message) throws Exception {
		String email = user.getEmail();
		NotificationDto notificationRequest = new NotificationDto(email, message);
		
//		Esta seria a implementação com um serviço de terceiro
		ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://localhost:8080/mocks", notificationRequest, String.class);
		
		if( !(notificationResponse.getStatusCode() == HttpStatus.OK) ) {
			throw new NotificationException("Serviço de notificação não está disponível.");
		}
	}
}
