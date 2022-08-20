package uol.compass.projetofinal.exceptionhandler;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ExceptionMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;

	public ExceptionMessage(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}