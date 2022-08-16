package uol.compass.projetofinal.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import uol.compass.projetofinal.dto.ProductDto;
import uol.compass.projetofinal.dto.ProductForm;
import uol.compass.projetofinal.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll() {
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return productService.findById(id);
	}
	
	@PostMapping @Transactional
	public ResponseEntity<ProductDto> create(@RequestBody @Validated ProductForm form, UriComponentsBuilder uriBuilder) {
		return productService.create(form, uriBuilder);
	}
	
	@DeleteMapping("/{id}") @Transactional
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		return productService.delete(id);
	}
}
