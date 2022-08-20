package uol.compass.projetofinal.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.dto.ProductForm;
import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.exceptionhandler.ProductNotFoundException;
import uol.compass.projetofinal.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Cacheable(value = "allProductsList")
	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}

	public ProductDto findById(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		return new ProductDto(product.orElseThrow(() -> new ProductNotFoundException()));
	}

	@Transactional
	@CacheEvict(value = "allProductsList", allEntries = true)
	public ProductDto create(ProductForm form) {
		Product product = new Product(form);
		productRepository.save(product);
		return new ProductDto(product);
	}

	@Transactional
	@CacheEvict(value = "allProductsList", allEntries = true)
	public void delete(Integer id) {
		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new ProductNotFoundException();
		}
	}

	@Transactional
	@CacheEvict(value = "allProductsList", allEntries = true)
	public ProductDto update(Integer id, ProductForm form) {
		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {
			product.get().setName(form.getName());
			product.get().setDescription(form.getDescription());
			product.get().setPrice(form.getPrice());
		}
		return new ProductDto(product.orElseThrow(() -> new ProductNotFoundException()));
	}

	public List<ProductDto> search(Double max_price, Double min_price, String name) {
		List<Product> products = productRepository.search(max_price, min_price, name);

		if (!products.isEmpty()) {
			return products.stream().map(ProductDto::new).collect(Collectors.toList());
		} else
			throw new ProductNotFoundException();
	}

}
