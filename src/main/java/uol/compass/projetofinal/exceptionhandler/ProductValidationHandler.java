package uol.compass.projetofinal.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProductValidationHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> productNotFound(ProductNotFoundException e, WebRequest request) {
		ExceptionMessage formError = new ExceptionMessage(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		return new ResponseEntity<Object>(formError, new HttpHeaders(), formError.getStatus());
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
		ExceptionMessage formError = new ExceptionMessage(HttpStatus.BAD_REQUEST, "the id should be an integer");
		return new ResponseEntity<Object>(formError, new HttpHeaders(), formError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage formError = new ExceptionMessage(HttpStatus.BAD_REQUEST, "one or more given parameters are not valid");
		return handleExceptionInternal(e, formError, headers, formError.getStatus(), request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage formError = new ExceptionMessage(HttpStatus.BAD_REQUEST, "the request is missing one or more parameters");
		return handleExceptionInternal(e, formError, headers, formError.getStatus(), request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(Exception e, WebRequest request) {
		ExceptionMessage formError = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, "an error ocurred");
		return new ResponseEntity<Object>(formError, new HttpHeaders(), formError.getStatus());
	}
}
