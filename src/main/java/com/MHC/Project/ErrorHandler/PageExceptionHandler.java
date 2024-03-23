package com.MHC.Project.ErrorHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.MHC.Project.Error.PageNotFound;

@RestControllerAdvice
public class PageExceptionHandler {

	@ExceptionHandler({ PageNotFound.class })
	public ResponseEntity<Map<String, String>> handleNotFoundException(RuntimeException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("ErrorMessage", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {
		Map<String, String> errorMap = new HashMap<>();
		if (ex instanceof MethodArgumentNotValidException) {
			((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(), error.getDefaultMessage());
			});
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
	}
}
