package uol.compass.projetofinal.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import uol.compass.projetofinal.entities.Product;

public class ProductForm {

	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty
	private String description;
	@NotNull
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
	
	public Product createProduct() {
		return new Product(name, description, price);
	}

}
