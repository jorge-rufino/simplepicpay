package com.rufino.simplepicpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rufino.simplepicpay.domain.User;
import com.rufino.simplepicpay.dto.NotificationDto;

@Service
public class NotificationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String message) throws Exception {
		String email = user.getEmail();
		NotificationDto notificationRequest = new NotificationDto(email, message);
		
		ResponseEntity<String> notificationResponse = restTemplate.postForEntity("Url do serviço", notificationRequest, String.class);
		
		if( !(notificationResponse.getStatusCode() == HttpStatus.OK) ) {
			throw new Exception("Serviço de notificação não está disponível.");
		}
	}
}
