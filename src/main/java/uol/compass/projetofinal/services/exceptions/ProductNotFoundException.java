package uol.compass.projetofinal.services.exceptions;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super("Product not found.");
	}
}
