package com.rufino.simplepicpay.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		
//		Como o método "postForEntity" dispara 2 exceptions diferentes (uma para codigo 4xx e outra para codigo 5xx) quando retorna um código diferente da faixa 2xx,
//		tratei a exceção direto na ApiExceptionHandler
		
//		Esta seria a implementação com um serviço de terceiro
//		ResponseEntity<Object> notificationResponse = restTemplate.postForEntity("http://localhost:8080/mocks", notificationRequest, Object.class);
		
		restTemplate.postForEntity("http://localhost:8080/mocks", notificationRequest, Object.class);
				
//		if( !(notificationResponse.getStatusCode() == HttpStatus.OK) ) {
//			throw new NotificationException("Serviço de notificação não está disponível.");
//		}
	}
}
