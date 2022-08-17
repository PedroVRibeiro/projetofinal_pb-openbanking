package uol.compass.projetofinal.exceptionhandler;

public class MessageExceptionError {

	private Integer status;
	private String message;

	public MessageExceptionError(Integer status, String message) {
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
