package uol.compass.projetofinal.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import uol.compass.projetofinal.services.exceptions.ProductNotFoundException;

@RestControllerAdvice
public class ProductValidationHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseBody
	@ExceptionHandler
	public ResponseEntity<MessageExceptionError> productNotFound(ProductNotFoundException productNotFoundException) {
		MessageExceptionError eh = new MessageExceptionError(HttpStatus.NOT_FOUND.value(), productNotFoundException.getMessage());
		return new ResponseEntity<MessageExceptionError>(eh, HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormError> handle(MethodArgumentNotValidException missingParamsException) {
		List<FormError> list = new ArrayList<>();
		
		List<FieldError> fieldErrors = missingParamsException.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormError error = new FormError(String.valueOf(HttpStatus.BAD_REQUEST), e.getField(), message);
			list.add(error);
		});
		return list;
	}
	
}
