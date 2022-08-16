package uol.compass.projetofinal.dto;

import java.util.List;
import java.util.stream.Collectors;

import uol.compass.projetofinal.entities.Product;

public class ProductDto {

	private Integer id;
	private String name;
	private String description;
	private Double price;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public static List<ProductDto> convert(List<Product> products) {
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}
	
	public static ProductDto convert(Product product) {
		return new ProductDto(product);
	}
}
