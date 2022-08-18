package uol.compass.projetofinal.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MissingParamsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MissingParamsException() {
		super("The parameters should not be empty.");
	}
}
