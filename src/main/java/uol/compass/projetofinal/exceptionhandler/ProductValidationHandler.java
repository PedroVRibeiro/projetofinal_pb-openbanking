package uol.compass.projetofinal.exceptionhandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import uol.compass.projetofinal.services.exceptions.ProductNotFoundException;

@RestControllerAdvice
public class ProductValidationHandler extends ResponseEntityExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ExceptionMessage> productNotFound(ProductNotFoundException productNotFoundException, WebRequest request) {
		ExceptionMessage em = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), productNotFoundException.getMessage());
		return new ResponseEntity<ExceptionMessage>(em, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionMessage> invalidParams(IllegalArgumentException illegalArgumentException, WebRequest request) {
		ExceptionMessage em = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), illegalArgumentException.getMessage());
		return new ResponseEntity<ExceptionMessage>(em, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, List<String>> body = new HashMap<>();
		
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
		
		body.put("400 - Bad Request", errors);
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
}
