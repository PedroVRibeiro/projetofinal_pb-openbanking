package uol.compass.projetofinal.services.exceptions;

public class MissingParamsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MissingParamsException() {
		super("The parameters should not be empty.");
	}
}
