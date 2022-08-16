package uol.compass.projetofinal.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.dto.ProductForm;
import uol.compass.projetofinal.entities.Product;
import uol.compass.projetofinal.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<List<ProductDto>> findAll() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok().body(ProductDto.convert(products));
	}
	
	public ResponseEntity<ProductDto> findById(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		
		if(product.isPresent()) {
			ProductDto found = ProductDto.convert(product.get());
			return ResponseEntity.ok().body(found);
		}
		
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<ProductDto> create(ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = form.createProduct();
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}

}
