package uol.compass.projetofinal.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductForm {

	@NotEmpty(message = "the name should not be empty")
	private String name;
	@NotEmpty(message = "the description should not be empty")
	private String description;
	@NotNull(message = "the price should not be empty")
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
