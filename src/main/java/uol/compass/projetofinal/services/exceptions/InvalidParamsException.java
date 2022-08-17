package uol.compass.projetofinal.services.exceptions;

public class InvalidParamsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidParamsException() {
		super("The parameters entered are not valid");
	}
}
