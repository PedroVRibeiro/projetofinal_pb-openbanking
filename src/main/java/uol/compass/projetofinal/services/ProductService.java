package uol.compass.projetofinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.dto.ProductForm;
import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;
import uol.compass.projetofinal.services.exceptions.ProductNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		return ProductDto.convert(products);
	}
	
	public ProductDto findById(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		return ProductDto.convert(product.orElseThrow(() -> new ProductNotFoundException()));
	}

	public ProductDto create(ProductForm form) {
		Product product = form.createProduct();
		productRepository.save(product);
		return new ProductDto(product);
	}

	public void delete(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		
		if(product.isPresent()) {
			productRepository.deleteById(id);
		}
	}

	public ProductDto update(Integer id, ProductForm form) {
		Optional<Product> product = productRepository.findById(id);
		
		if(product.isPresent()) {
			return form.updateProduct(product, productRepository);
		} 
		throw new ProductNotFoundException();
	}

	public List<ProductDto> search(Double max_price, Double min_price, String name) {
		List<Product> products = productRepository.search(max_price, min_price, name);
		
		if(!products.isEmpty()) {
			return ProductDto.convert(products);
		}
		throw new ProductNotFoundException();
	}

}
