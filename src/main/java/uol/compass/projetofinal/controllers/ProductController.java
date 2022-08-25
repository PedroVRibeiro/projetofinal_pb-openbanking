package uol.compass.projetofinal.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	public ResponseEntity<Page<ProductDto>> findAll(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 2) Pageable pageable) {
		return ResponseEntity.ok().body(productService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(productService.findById(id));
	}

	@GetMapping("/search")
	public ResponseEntity<Page<ProductDto>> search(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 2) Pageable pageable,
			@RequestParam(required = false) Double max_price,
			@RequestParam(required = false) Double min_price, 
			@RequestParam(required = false) String name) {
		return ResponseEntity.ok().body(productService.search(max_price, min_price, name, pageable));
	}

	@PostMapping
	public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductForm form) {
		ProductDto productDto = productService.create(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/products/{id}")
				.buildAndExpand(productDto.getId()).toUri();
		return ResponseEntity.created(uri).body(productDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> update(@PathVariable Integer id, @Valid @RequestBody ProductForm form) {
		return ResponseEntity.ok().body(productService.update(id, form));
	}
}
