package uol.compass.projetofinal.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		return ResponseEntity.ok().body(productService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(productService.findById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDto>> search(
			@RequestParam(required = false) Double max_price, 
			@RequestParam(required = false) Double min_price, 
			@RequestParam(required = false) String name) {
		return ResponseEntity.ok().body(productService.search(max_price, min_price, name));
	}
	
	@PostMapping @Transactional
	public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductForm form) {
		ProductDto productDto = productService.create(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}") @Transactional
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}") @Transactional
	public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody ProductForm form) {
		return ResponseEntity.ok().body(productService.update(id, form));
	}
}
