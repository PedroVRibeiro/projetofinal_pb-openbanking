package uol.compass.projetofinal.exceptionhandler;

import java.io.Serializable;

public class ExceptionMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;

	public ExceptionMessage(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
