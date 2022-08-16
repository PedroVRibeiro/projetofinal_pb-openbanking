package uol.compass.projetofinal.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;

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

	public ProductDto updateProduct(Integer id, ProductRepository productRepository) {
		Optional<Product> product = productRepository.findById(id);
		product.get().setName(this.name);
		product.get().setDescription(this.description);
		product.get().setPrice(this.price);
		return new ProductDto(product.get());
	}

}
