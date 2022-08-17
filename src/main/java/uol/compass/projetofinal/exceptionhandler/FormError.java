package uol.compass.projetofinal.exceptionhandler;

public class FormError {

	private String status;
	private String field;
	private String message;

	public FormError(String status, String field, String message) {
		this.status = status;
		this.field = field;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
