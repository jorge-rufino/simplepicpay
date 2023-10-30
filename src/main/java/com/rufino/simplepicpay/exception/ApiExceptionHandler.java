package com.rufino.simplepicpay.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.rufino.simplepicpay.dto.ExceptionDto;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handlerDataIntegrityViolationException(DataIntegrityViolationException e) {

		ExceptionDto exceptionDto = new ExceptionDto("Usuário já cadastrado!", "400");
		return ResponseEntity.badRequest().body(exceptionDto);
	}

	// Erro codigo 4xx do serviço de Notificacao
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> handlerHttpClientErrorException(HttpClientErrorException e) {

		ExceptionDto exceptionDto = new ExceptionDto("Serviço de notificação está indisponível!", "500");
		return ResponseEntity.internalServerError().body(exceptionDto);
	}

	// Erro codigo 5xx do serviço de Notificacao
	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<?> HttpServerErrorException(HttpServerErrorException e) {

		ExceptionDto exceptionDto = new ExceptionDto("Serviço de notificação está indisponível!", "500");
		return ResponseEntity.internalServerError().body(exceptionDto);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {

		ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), "404"
				+ ""
				+ "");

		return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SaldoInsuficienteException.class)
	public ResponseEntity<?> handlerSaldoInsuficienteException(SaldoInsuficienteException e) {

		ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), "400");

		return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MerchantException.class)
	public ResponseEntity<?> handlerMerchantException(MerchantException e) {

		ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), "400");

		return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<?> handlerTransactionException(TransactionException e) {

		ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), "401");

		return new ResponseEntity<>(exceptionDto, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerException(Exception e) {

		ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), "500");

		return ResponseEntity.internalServerError().body(exceptionDto);
	}
}
